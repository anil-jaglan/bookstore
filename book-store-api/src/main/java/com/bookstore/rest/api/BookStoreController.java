package com.bookstore.rest.api;

import com.bookstore.rest.constants.ApiConstants;
import com.bookstore.rest.transferobjects.BookBasicInfoTO;
import com.bookstore.rest.transferobjects.ResponseTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * API layer interface for exposing bookstore end points.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public interface BookStoreController {

    /**
     * 
     * Method to add a new book entry in book-store.
     * 
     * @return
     */
    @PostMapping(value = "/book", consumes = ApiConstants.EXTERNAL_MEDIA_TYPE, produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    ResponseEntity<ResponseTO> addBook(@RequestBody BookBasicInfoTO book);

    /**
     * 
     * Method to update new book entry in book-store.
     * 
     * @return
     */
    @PutMapping(value = "/book/{isbn}", produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    ResponseEntity<ResponseTO> updateBook(@PathVariable("isbn") String isbn, @RequestParam("quantity") int quantity);

    /**
     * 
     * Method to search a book by given search key matching with ISBN, title or
     * author.
     * 
     * @param searchkey
     *            - string value to be searched by.
     * @return - Set of matched books.
     */
    @GetMapping(value = "/book/{searchKey}", produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    ResponseEntity<ResponseTO> searchBook(@PathVariable("searchKey") String searchKey);

    /**
     * 
     * Method to buy book from store.
     * 
     * NOTE: CURRENTLY, WE ARE ALLOWING BUYER TO BUY ONLY ONE BOOK AT A TIME
     * WITH MULTIPLE COPIES.
     * 
     * @param isbn
     *            - Book isbn number.
     * @param quantity
     *            - Quantity to be purchased.
     * @return - book invoice details.
     */
    @PostMapping(value = "/book/{isbn}/buy", produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    ResponseEntity<ResponseTO> buy(@PathVariable("isbn") String isbn, @RequestParam("quantity") int quantity);

    /**
     * 
     * Method to search a book media coverage by given ISBN
     * 
     * @param isbn
     *            - isbn number.
     * @return - Set of matched books.
     */
    @GetMapping(value = "/book/{isbn}/media", produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    ResponseEntity<ResponseTO> mediaCoverage(@PathVariable("isbn") String isbn);
}
