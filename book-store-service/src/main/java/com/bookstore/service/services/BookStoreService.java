package com.bookstore.service.services;

import com.bookstore.rest.transferobjects.BookBasicInfoTO;
import com.bookstore.rest.transferobjects.BookInfoResponseTO;
import com.bookstore.rest.transferobjects.BookInvoiceTO;

import java.util.Set;

/**
 * 
 * Book-store service layer interface.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public interface BookStoreService {

    /**
     * 
     * Method to add a new book in bookstore.
     * 
     * @param book
     *            - book details.
     */
    void add(BookBasicInfoTO book);

    /**
     * 
     * Method to update book quantity.
     * 
     * @param isbn
     *            - ISBN number.
     * @param quantity
     *            - No. of quantity.
     */
    void update(String isbn, int quantity);

    /**
     * 
     * Method to add a new book in bookstore.
     * 
     * @param isbn
     *            - isbn number.
     * @param quantity
     *            - Quantity to be purchased.
     */
    BookInvoiceTO buy(String isbn, int quantity);

    /**
     * 
     * Method to search a book by matching searchKey in ISBN, Author and title.
     * 
     * @param searchKey
     * @return
     */
    Set<BookInfoResponseTO> search(String searchKey);
}
