package com.bookstore.service.services.impl;

import com.bookstore.common.exceptions.BookStoreExceptionEnum;
import com.bookstore.common.exceptions.ExtendedRuntimeException;
import com.bookstore.service.businessobjects.PostBO;
import com.bookstore.service.domainmodel.entity.BookEntity;
import com.bookstore.service.domainmodel.repository.BookStoreRepository;
import com.bookstore.service.services.MediaConverageService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Media coverage service implementation class.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Service
public class MediaCoverageServiceImpl implements MediaConverageService {

    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaCoverageServiceImpl.class);

    /**
     * Injecting bookstore repository instance.
     */
    @Autowired
    private BookStoreRepository bookStoreRepository;

    /**
     * Rest Template instance for calling external service.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Media API URL.
     */
    @Value("${media.api.url:https://jsonplaceholder.typicode.com/posts}")
    private String mediaApiURL;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Collection<String> getMediaCoverage(final String isbn) {
        LOGGER.info("Searching media coverage for book: {}", isbn);
        final Optional<BookEntity> book = bookStoreRepository.findByIsbn(isbn);
        List<String> matchedPosts = Collections.<String>emptyList();
        if (book.isPresent()) {
            final String bookTitle = book.get().getTitle();
            LOGGER.info("Consuming Rest API : {}", mediaApiURL);
            final ResponseEntity<PostBO[]> response = restTemplate.getForEntity(mediaApiURL, PostBO[].class);
            final List<PostBO> allPosts = Arrays.asList(response.getBody());
            LOGGER.info("Media posts size: {}", allPosts.size());
            matchedPosts = allPosts.parallelStream().filter(
                    post -> post.getTitle().contains(bookTitle) || post.getBody().contains(bookTitle)).collect(
                            Collectors.toList()).stream().map(PostBO::getTitle).collect(Collectors.toList());

            LOGGER.info("Media post matched with book: {}, Media coverage list size: {}", isbn, matchedPosts.size());
        } else {
            LOGGER.error("Book not available in store with given ISBN: {}", isbn);
            throw new ExtendedRuntimeException(BookStoreExceptionEnum.BOOK_NOT_AVAILABLE);
        }
        return matchedPosts;
    }

    /**
     * 
     * Getter of {@link #mediaApiURL}.
     * 
     * @return {@link #mediaApiURL}
     */
    public String getMediaApiURL() {
        return mediaApiURL;
    }

    /**
     * 
     * Setter of {@link #mediaApiURL}.
     * 
     * @param mediaApiURL
     *            to be set
     */
    public void setMediaApiURL(String mediaApiURL) {
        this.mediaApiURL = mediaApiURL;
    }

}
