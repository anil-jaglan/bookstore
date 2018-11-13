package com.bookstore.service.services;

import java.util.Collection;

/**
 * 
 * Book media converage search service interface.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public interface MediaConverageService {

    /**
     * 
     * Method to find media coverage of a book by loading books from store and
     * matching book title with public post API data.
     * 
     * @param isbn
     *            - Book ISBN number.
     * @return
     */
    Collection<String> getMediaCoverage(String isbn);
}
