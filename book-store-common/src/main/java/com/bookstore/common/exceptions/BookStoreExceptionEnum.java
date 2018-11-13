package com.bookstore.common.exceptions;

import com.bookstore.common.errors.ErrorData;
import com.bookstore.common.errors.ErrorInterface;

/**
 * 
 * Generic exception messages class.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public enum BookStoreExceptionEnum implements ErrorInterface {

    BOOK_NOT_AVAILABLE("BK-001", "Book not available"), 
    INVALID_GENRE_NAME("BK-002", "Invalid genre name"), 
    OUT_OF_STOCK("BK-003", "This book is out of stock as of now"),
    QUANTITY_EXCEEDING("BK-004", "No. of quantities exceeding"),
    BOOK_ALREADY_EXISTING("BK-005", "Book already existing in store with given IBSN"),
    BOOK_SEARCH_ERROR("BK-006","Sorry, can not search book for you. Search service is not working currently."),
    DB_LOCK_ERROR("BK-007","Error while processing your request.");

    /**
     * Message code.
     */
    private String code;

    /**
     * Message.
     */
    private String message;

    /**
     * 
     * Constructor.
     *
     * @param code
     *            - Message code.
     * @param message
     *            - Message.
     */
    private BookStoreExceptionEnum(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ErrorData getErrorData() {
        return new ErrorData(code, message);
    }
}
