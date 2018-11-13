package com.bookstore.service.converters;

import com.bookstore.common.converter.AbstractConverter;
import com.bookstore.common.enums.GenreEnum;
import com.bookstore.rest.transferobjects.BookBasicInfoTO;
import com.bookstore.service.domainmodel.entity.BookEntity;

import org.springframework.stereotype.Component;

/**
 * 
 * Converter to convert BookBasicInfoTO object to BookEntity.
 * 
 * NOTE: WE CAN MAKE TARGET OBJECT AS {@code java.util.Optional<>} AS WELL, IT
 * WILL HELP US IN HANDLING NULL POINTER EXCEPTIONS.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Component
public class BookBasicInfoToBookEntityConverter extends AbstractConverter<BookBasicInfoTO, BookEntity> {

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public BookEntity convert(final BookBasicInfoTO source) {
        BookEntity book = null;
        if (source != null) {
            book = new BookEntity();
            book.setIsbn(source.getIsbn());
            book.setAuthor(source.getAuthor());
            book.setPrice(source.getPrice());
            book.setGenre(GenreEnum.getByName(source.getGenre()));
            book.setDesc(source.getDesc());
            book.setPublishDate(source.getPublishDate());
            book.setTotalCopies(source.getTotalCopies());
            book.setTitle(source.getTitle());
            book.setSoldCopies(0);
        }
        return book;
    }

}
