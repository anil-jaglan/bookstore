package com.bookstore.service.services.impl;

import com.bookstore.common.converter.Converter;
import com.bookstore.rest.transferobjects.BookBasicInfoTO;
import com.bookstore.rest.transferobjects.BookInvoiceTO;
import com.bookstore.service.domainmodel.entity.BookEntity;
import com.bookstore.service.services.BookInvoiceService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * BookInvoiceService implementation class to generate invoice.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Service
public class BookInvoiceServiceImpl implements BookInvoiceService {

    /**
     * Injecting converter instance.
     */
    @Autowired
    private Converter<BookEntity, BookBasicInfoTO> converter;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public BookInvoiceTO generateInvoice(final BookEntity book, final Long purchaseId, final int quantity) {
        final BookInvoiceTO invoice = new BookInvoiceTO();
        invoice.setInvoiceId("INV-" + purchaseId);
        invoice.setInvoiceDate(LocalDate.now().toString());
        invoice.setItem(converter.convert(book));
        invoice.setInvoiceValue(book.getPrice() * quantity);
        return invoice;
    }

}
