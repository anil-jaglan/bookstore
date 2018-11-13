package com.bookstore.service.services.impl;

import com.bookstore.service.domainmodel.entity.BookEntity;
import com.bookstore.service.domainmodel.repository.BookStoreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * Service class to search asynchronously.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Service
public class AsyncSearchService {

    /**
     * Injecting Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSearchService.class);

    /**
     * Injecting BookingStoreRepository instance.
     */
    @Autowired
    private BookStoreRepository bookRepository;

    /**
     * 
     * Method to search books by author asynchronously..
     * 
     * @param isbn
     * @param books
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public CompletableFuture<List<BookEntity>> searchByAuthor(final String author) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Async Searching by author: {}", author);
            return bookRepository.findByAuthorIgnoreCaseContaining(author);
        });
    }

    /**
     * 
     * Method to search books by title asynchronously.
     * 
     * @param isbn
     * @param books
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public CompletableFuture<List<BookEntity>> searchByTitle(final String title) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Async Searching by title: {}", title);
            return bookRepository.findByTitleIgnoreCaseContaining(title);
        });
    }

    /**
     * 
     * Method to search books by ISBN asynchronously.
     * 
     * @param isbn
     * @param books
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public CompletableFuture<List<BookEntity>> searchByISBN(final String isbn) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Async Searching by isbn: {}", isbn);
            final Optional<BookEntity> book = bookRepository.findByIsbn(isbn);
            List<BookEntity> books = Collections.emptyList();
            if (book.isPresent()) {
                books = new ArrayList<>();
                books.add(book.get());
            }
            return books;
        });
    }
}
