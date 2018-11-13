package com.bookstore.service.services.impl;

import com.bookstore.common.converter.Converter;
import com.bookstore.common.exceptions.BookStoreExceptionEnum;
import com.bookstore.common.exceptions.ExtendedRuntimeException;
import com.bookstore.rest.transferobjects.BookBasicInfoTO;
import com.bookstore.rest.transferobjects.BookInfoResponseTO;
import com.bookstore.rest.transferobjects.BookInvoiceTO;
import com.bookstore.service.domainmodel.entity.BookEntity;
import com.bookstore.service.domainmodel.entity.BookPurchaseEntity;
import com.bookstore.service.domainmodel.repository.BookPurchaseRepository;
import com.bookstore.service.domainmodel.repository.BookStoreRepository;
import com.bookstore.service.services.BookInvoiceService;
import com.bookstore.service.services.BookStoreService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.persistence.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * BookStore service layer implementation.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Service
public class BookStoreServiceImpl implements BookStoreService {

    /**
     * Injecting Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);

    /**
     * Injecting BookingStoreRepository instance.
     */
    @Autowired
    private BookStoreRepository bookRepository;

    /**
     * Injecting BookPurchaseRepository instance.
     */
    @Autowired
    private BookPurchaseRepository purchaseRepository;

    /**
     * Injecting BookInfo request TO to BookEntity converter.
     */
    @Autowired
    private Converter<BookBasicInfoTO, BookEntity> bookRequestToEntityConverter;

    /**
     * Injecting BookInvoiceService instance.
     */
    @Autowired
    private BookInvoiceService invoiceService;

    /**
     * Async search service instance.
     */
    @Autowired
    private AsyncSearchService asyncSearchService;

    /**
     * Converter instance.
     */
    @Autowired
    private Converter<BookEntity, BookInfoResponseTO> converter;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void add(final BookBasicInfoTO bookTO) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Saving Book into data store: {}", bookTO);
        }
        final Optional<BookEntity> book = bookRepository.findByIsbn(bookTO.getIsbn());
        if (book.isPresent()) {
            LOGGER.error("Book already existing for ISBN: {}", bookTO.getIsbn());
            throw new ExtendedRuntimeException(BookStoreExceptionEnum.BOOK_ALREADY_EXISTING);
        }
        bookRepository.save(bookRequestToEntityConverter.convert(bookTO));
        LOGGER.info("Book saved successfully in book store with ISBN: {}", bookTO.getIsbn());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public void update(String isbn, int quantity) {
        LOGGER.debug("Updating Book into data store ISBN: {}, quantity: {}", isbn, quantity);
        final Optional<BookEntity> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            final int oldQuantity = book.get().getTotalCopies();
            bookRepository.updateQuantity(isbn, quantity);
            LOGGER.info("Book quantity updated from {} to {} for ISBN: {}", oldQuantity, quantity, isbn);
        } else {
            LOGGER.error("Book doesn't exist with given ISBN: {}", isbn);
            throw new ExtendedRuntimeException(BookStoreExceptionEnum.BOOK_NOT_AVAILABLE);
        }

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Set<BookInfoResponseTO> search(final String searchKey) {
        LOGGER.info("Searching book for: {}", searchKey);
        final List<BookEntity> books = new ArrayList<>();
        final List<CompletableFuture<List<BookEntity>>> futureList = new ArrayList<>();
        LOGGER.debug("Searching for isbn: {}, size: {}", searchKey, books.size());
        futureList.add(asyncSearchService.searchByISBN(searchKey));
        LOGGER.debug("Searching for author: {}, size: {}", searchKey, books.size());
        futureList.add(asyncSearchService.searchByAuthor(searchKey));
        LOGGER.debug("Searching for title: {}, size: {}", searchKey, books.size());
        futureList.add(asyncSearchService.searchByTitle(searchKey));
        LOGGER.info("Search completed for: {}, search size: {}", searchKey, books.size());
        
        for (final CompletableFuture<List<BookEntity>> future : futureList) {
            try {
                books.addAll(future.get());
            } catch (final InterruptedException | ExecutionException e) {
                LOGGER.error("Error occured while searching books for: {}, Error: {}", searchKey, e);
                throw new ExtendedRuntimeException(BookStoreExceptionEnum.BOOK_SEARCH_ERROR);
            }
        }
        return new HashSet<>(converter.convert(books));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Retryable(value =
        { OptimisticLockException.class }, maxAttempts = 3)
    public BookInvoiceTO buy(final String isbn, final int quantity) {
        // Check logging level is enabled for costly statements.
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Processing book buying request ISBN: {}, quantity: {}", isbn, quantity);
        }
        BookEntity book = null;
        Long purchaseId = null;
        final Optional<BookEntity> bookEntity = bookRepository.findByIsbn(isbn);
        book = bookEntity.orElseThrow(() -> new ExtendedRuntimeException(BookStoreExceptionEnum.BOOK_NOT_AVAILABLE));
        LOGGER.info("Book present in book store for ISBN: {}", isbn);

        if (areCopiesAvailable(book, quantity)) {
            book.setSoldCopies(book.getSoldCopies() + quantity);
            bookRepository.save(book);
            purchaseId = savePurchaseInfo(isbn, quantity);
        }
        LOGGER.info("Book purchase entry added in book store. Generating invoice for ISBN [{}]", isbn);
        return invoiceService.generateInvoice(book, purchaseId, quantity);
    }

    /**
     * 
     * Method to check if demanded copies of a book are available in store.
     * 
     * @param book
     *            - Book to be purchased.
     * @param demandCopies
     *            - Number of copies to be purchased.
     * @return true if available else false.
     */
    protected boolean areCopiesAvailable(final BookEntity book, final int demandCopies) {
        final int unSoldCopies = getUnsoldCopies(book);
        LOGGER.debug("Book[ISBN: {}] copies available in store: {}", book.getIsbn(), unSoldCopies);
        if (demandCopies <= unSoldCopies) {
            return true;
        } else if (unSoldCopies == 0) {
            LOGGER.error("Book[ISBN: {}] is out of stock.", book.getIsbn());
            throw new ExtendedRuntimeException(BookStoreExceptionEnum.OUT_OF_STOCK);
        } else {
            LOGGER.error(
                    "Book[ISBN: {}] purchase quantity exceeding. Available: {}, Demanded: {}",
                    book.getIsbn(),
                    unSoldCopies,
                    demandCopies);
            throw new ExtendedRuntimeException(BookStoreExceptionEnum.QUANTITY_EXCEEDING);
        }
    }

    /**
     * 
     * Method to store book purchase information.
     * 
     * @param isbn
     * @param noOfCopies
     * @return - purchaseId.
     */
    private Long savePurchaseInfo(final String isbn, final int noOfCopies) {
        BookPurchaseEntity purchaseInfo = new BookPurchaseEntity(isbn, noOfCopies);
        purchaseInfo = purchaseRepository.save(purchaseInfo);
        LOGGER.info(
                "Book[ISBN: {}] purchases with '{}' copies saving with purchaseID: {}",
                isbn,
                noOfCopies,
                purchaseInfo.getPurchaseId());
        return purchaseInfo.getPurchaseId();
    }

    /**
     * 
     * Method to find remaining unsold copies of a book in store.
     * 
     * @param book
     *            - Book entity.
     * @return - Total copies minus sold copies.
     */
    private int getUnsoldCopies(final BookEntity book) {
        return book.getTotalCopies() - book.getSoldCopies();
    }
}
