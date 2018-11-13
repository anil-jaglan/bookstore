package com.bookstore.service.domainmodel.repository;

import com.bookstore.service.domainmodel.entity.BookEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * BookStore repository interface to persist/retrieve book entities.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Repository
public interface BookStoreRepository extends CrudRepository<BookEntity, Long> {

    /**
     * 
     * Method to update book total number of copies..
     * 
     * @param isbn
     *            - ISBN number.
     * @param quantity
     *            - Quantity to be updated.
     */
    @Modifying
    @Query("Update BookEntity be set be.totalCopies=?2 where be.isbn=?1")
    void updateQuantity(String isbn, int quantity);

    /**
     * 
     * Method to find book by ISBN number.
     * 
     * @param isbn
     *            - ISBN number.
     * @return - Book entity if matches.
     */
    Optional<BookEntity> findByIsbn(String isbn);

    /**
     * 
     * Method to find book by matching any part of author name.
     * 
     * @param author
     *            - author name.
     * @return - Book entities if matches any.
     */
    List<BookEntity> findByAuthorIgnoreCaseContaining(String author);

    /**
     * 
     * Method to find book by matching any part of title.
     * 
     * @param title
     *            - title.
     * @return - Book entities if matches any.
     */
    List<BookEntity> findByTitleIgnoreCaseContaining(String title);

}
