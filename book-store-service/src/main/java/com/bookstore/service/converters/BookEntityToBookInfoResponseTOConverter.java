package com.bookstore.service.converters;

import com.bookstore.common.converter.AbstractConverter;
import com.bookstore.rest.transferobjects.BookInfoResponseTO;
import com.bookstore.service.domainmodel.entity.BookEntity;

import org.springframework.stereotype.Component;

/**
 * 
 * Converter class for converting BookEntity to BookInfoResponseTO object.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Component
public class BookEntityToBookInfoResponseTOConverter extends AbstractConverter<BookEntity, BookInfoResponseTO> {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public BookInfoResponseTO convert(final BookEntity source) {
        BookInfoResponseTO book = null;
        if (source != null) {
            book = new BookInfoResponseTO();
            book.setIsbn(source.getIsbn());
            book.setAuthor(source.getAuthor());
            book.setPrice(source.getPrice());
            book.setGenre(source.getGenre().getName());
            book.setPublishDate(source.getPublishDate());
            book.setTitle(source.getTitle());
            book.setUnitsInStock(source.getTotalCopies() - source.getSoldCopies());
        }
        return book;
    }

}
