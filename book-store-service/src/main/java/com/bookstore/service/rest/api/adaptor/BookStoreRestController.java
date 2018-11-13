package com.bookstore.service.rest.api.adaptor;

import com.bookstore.rest.api.BookStoreController;
import com.bookstore.rest.transferobjects.BookBasicInfoTO;
import com.bookstore.rest.transferobjects.BookInfoResponseTO;
import com.bookstore.rest.transferobjects.BookInvoiceTO;
import com.bookstore.rest.transferobjects.ResponseTO;
import com.bookstore.service.services.BookStoreService;
import com.bookstore.service.services.MediaConverageService;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * 
 * API layer implementation for BookStoreController.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@RestController
public class BookStoreRestController implements BookStoreController {

    /**
     * Injecting BookStoreService instance.
     */
    @Autowired
    private BookStoreService bookStoreService;

    /**
     * Injecting MediaConverageService instance.
     */
    @Autowired
    private MediaConverageService mediaService;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ResponseTO> addBook(@Valid @RequestBody final BookBasicInfoTO book) {
        bookStoreService.add(book);
        final Link selfLink = getBuyLink(book.getIsbn());
        return new ResponseEntity<>(new ResponseTO(selfLink), HttpStatus.OK);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ResponseTO> updateBook(@PathVariable("isbn") final String isbn,
            @RequestParam("quantity") final int quantity) {
        bookStoreService.update(isbn, quantity);
        return new ResponseEntity<>(new ResponseTO(getBuyLink(isbn)), HttpStatus.OK);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ResponseTO> searchBook(@PathVariable("searchKey") final String searchKey) {
        final Set<BookInfoResponseTO> books = bookStoreService.search(searchKey);
        assembleBuyLink(books);
        return new ResponseEntity<>(new ResponseTO(books), HttpStatus.OK);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ResponseTO> buy(@PathVariable("isbn") String isbn, @RequestParam("quantity") int quantity) {
        final BookInvoiceTO invoice = bookStoreService.buy(isbn, quantity);
        return new ResponseEntity<>(new ResponseTO(invoice), HttpStatus.OK);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ResponseTO> mediaCoverage(@PathVariable("isbn") String isbn) {
        final Collection<String> mediaCoverage = mediaService.getMediaCoverage(isbn);
        return new ResponseEntity<>(new ResponseTO(mediaCoverage), HttpStatus.OK);
    }

    /**
     * 
     * Method to embed book by rel-link.
     * 
     * @param book
     *            - Book object.
     */
    protected void assembleBuyLink(final BookInfoResponseTO book) {
        assembleBuyLink(Collections.singletonList(book));
    }

    /**
     * 
     * Overloaded Method to embed book by rel-link.
     * 
     * @param books
     *            - Collection of books.
     */
    protected void assembleBuyLink(final Collection<BookInfoResponseTO> books) {
        books.forEach(book -> book.add(getBuyLink(book.getIsbn())));
    }

    /**
     * 
     * Method to get book buy rel-link.
     * 
     * @param isbn
     *            - isbn number.
     * 
     * @return - Link
     */
    protected Link getBuyLink(final String isbn) {
        return linkTo(methodOn(BookStoreRestController.class, new Object()).buy(isbn, 1)).withRel("buy");
    }

    /**
     * 
     * Method to get book buy rel-link.
     * 
     * @param isbn
     *            - isbn number.
     * 
     * @return - Link
     */
    protected Link getSearchLink(final String isbn) {
        return linkTo(methodOn(BookStoreRestController.class, new Object()).searchBook(isbn)).withRel("search");
    }

}
