package com.bookstore.service.converters;

import com.bookstore.common.converter.AbstractConverter;
import com.bookstore.rest.transferobjects.BookBasicInfoTO;
import com.bookstore.service.domainmodel.entity.BookEntity;

import org.springframework.stereotype.Component;

/**
 * 
 * Converter class to convert BookEntity data to BookBasicInfo TO.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Component
public class BookEntityToBookBasicInfoConverter extends AbstractConverter<BookEntity, BookBasicInfoTO> {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public BookBasicInfoTO convert(final BookEntity source) {
        BookBasicInfoTO book = null;
        if (source != null) {
            book = new BookBasicInfoTO();
            book.setIsbn(source.getIsbn());
            book.setAuthor(source.getAuthor());
            book.setPublishDate(source.getPublishDate());
            book.setDesc(source.getDesc());
            book.setGenre(source.getGenre().getName());
            book.setPrice(source.getPrice());
            book.setTitle(source.getTitle());
        }
        return book;
    }

}
