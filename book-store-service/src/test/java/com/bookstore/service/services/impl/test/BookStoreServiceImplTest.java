package com.bookstore.service.services.impl.test;

import com.bookstore.common.converter.Converter;
import com.bookstore.common.enums.GenreEnum;
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
import com.bookstore.service.services.impl.AsyncSearchService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 
 * Class to test book store data service layer integration..
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreServiceImplTest {

    /**
     * Injecting testable unit instance.
     */
    @Autowired
    private BookStoreService bookStoreService;

    /**
     * Injection Bookstore repository.
     */
    @MockBean
    private BookStoreRepository bookRepository;

    /**
     * Injection Book purchase repository.
     */
    @MockBean
    private BookPurchaseRepository bookPurchaseRepository;

    /**
     * Mock instance of converter class.
     */
    @MockBean
    private Converter<BookBasicInfoTO, BookEntity> bookRequestToEntityConverter;

    /**
     * Mocking Book invoice service bean.
     */
    @MockBean
    private BookInvoiceService invoiceService;

    /**
     * Mocking Async search service instance.
     */
    @MockBean
    private AsyncSearchService asyncSearchService;

    /**
     * Converter instance.
     */
    @MockBean
    private Converter<BookEntity, BookInfoResponseTO> converter;

    /**
     * 
     * Mock data and method calls.
     *
     */
    @Before
    public void setUp() {
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.ofNullable(null));
        when(bookRequestToEntityConverter.convert(any(BookBasicInfoTO.class))).thenReturn(createDummyBook());
        when(bookRepository.save(any(BookEntity.class))).thenReturn(createDummyBook());
        when(converter.convert(any(BookEntity.class))).thenReturn(null);
    }

    /**
     * 
     * Method to test if a book has been added successfully.
     *
     */
    @Test
    public void testAddBookWhenSuccess() {

        bookStoreService.add(prepareBookTO());
        verify(bookRepository, times(1)).save(any());
        verify(bookRepository, times(1)).findByIsbn(anyString());
    }

    /**
     * 
     * Method to test when adding a book with duplicate ISBN number.
     *
     */
    @Test(expected = ExtendedRuntimeException.class)
    public void testAddBookWhenDuplicate() {
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.of(createDummyBook()));
        bookStoreService.add(prepareBookTO());
    }

    /**
     * 
     * Method to test when buy available book.
     *
     */
    @Test
    public void testBuyBook() {
        final long purchaseId = 100l;

        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.of(createDummyBook()));
        when(invoiceService.generateInvoice(any(BookEntity.class), anyLong(), anyInt())).thenReturn(
                createBookInvoice(purchaseId, 5.0f, 5));
        final BookPurchaseEntity bookPurchased = new BookPurchaseEntity("ISBN0001", 5);
        bookPurchased.setPurchaseId(purchaseId);
        when(bookPurchaseRepository.save(any(BookPurchaseEntity.class))).thenReturn(bookPurchased);
        final BookInvoiceTO bookInvoice = bookStoreService.buy("ISBN0001", 5);

        assertEquals(25.0f, bookInvoice.getInvoiceValue(), 0.0f);
        assertEquals("INV-100", bookInvoice.getInvoiceId());
    }

    /**
     * 
     * Method to test when buy a book which is out of stock.
     *
     */
    @Test(expected = ExtendedRuntimeException.class)
    public void testBuyBookWhenOutOfStock() {
        final BookEntity book = new BookEntity();
        book.setTotalCopies(5);
        book.setSoldCopies(5);
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.of(book));

        bookStoreService.buy("ISBN0001", 5);

        verify(bookRepository, times(1)).findByIsbn(anyString());
    }

    /**
     * 
     * Method to test when searching a book.
     *
     */
    @Test
    public void testSearchBook() {

        try {
            doNothing().when(asyncSearchService).searchByISBN(anyString(), any());
            doNothing().when(asyncSearchService).searchByTitle(anyString(), any());
            doNothing().when(asyncSearchService).searchByAuthor(anyString(), any());
        } catch (InterruptedException | ExecutionException e) {
        }

        final Set<BookInfoResponseTO> books = bookStoreService.search("ISBN0001");

    }

    /**
     * 
     * Method to create dummy books entries.
     * 
     * @return
     */
    public BookBasicInfoTO prepareBookTO() {
        final BookBasicInfoTO book = new BookBasicInfoTO();
        book.setIsbn("ISBN0001");
        book.setTitle("Harry Potter");
        book.setAuthor("J K. Rowling");
        book.setPrice(14.95f);
        book.setGenre(GenreEnum.FANTASY.getName());
        book.setTotalCopies(10);
        book.setPublishDate(LocalDate.of(2005, 1, 1));
        return book;
    }

    /**
     * 
     * Method to create dummy book entry.
     * 
     * @return
     */
    public BookEntity createDummyBook() {
        final BookEntity book = new BookEntity();
        book.setIsbn("ISBN0000099");
        book.setAuthor("Chetan Bhagat");
        book.setPrice(5.0f);
        book.setTitle("Half Girlfriend");
        book.setGenre(GenreEnum.ROMANCE);
        book.setTotalCopies(100);
        return book;
    }

    /**
     * 
     * Method to create dummy invoice TO.
     * 
     * @param purchaseId
     * @param price
     * @param quantity
     * @return
     */
    public BookInvoiceTO createBookInvoice(final Long purchaseId, final float price, final int quantity) {
        final BookInvoiceTO invoice = new BookInvoiceTO();
        invoice.setInvoiceId("INV-" + purchaseId);
        invoice.setInvoiceValue(price * quantity);
        return invoice;
    }
}
