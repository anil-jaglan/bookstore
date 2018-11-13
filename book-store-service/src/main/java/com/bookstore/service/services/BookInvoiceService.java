package com.bookstore.service.services;

import com.bookstore.rest.transferobjects.BookInvoiceTO;
import com.bookstore.service.domainmodel.entity.BookEntity;

/**
 * 
 * Service layer interface for Invoice generation.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public interface BookInvoiceService {

    /**
     * 
     * Method to generate invoice for given book.
     * 
     * @param book
     *            - Book entity object.
     * @param purchaseId
     *            - Book purchase id.
     * @param quantity
     *            - Purchased Quantity.
     * @return - BookInvoiceTO object.
     */
    BookInvoiceTO generateInvoice(BookEntity book, Long purchaseId, int quantity);
}
