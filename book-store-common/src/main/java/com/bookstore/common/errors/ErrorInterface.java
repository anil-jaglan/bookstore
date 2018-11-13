package com.bookstore.common.errors;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * 
 * Error interface to print error messages of exception occurred in
 * application..
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public interface ErrorInterface extends Serializable {

    /**
     * @return error code.
     */
    String getCode();

    /**
     * @return error message.
     */
    String getMessage();

    /**
     * @return error data.
     */
    ErrorData getErrorData();

    /**
     * 
     * This method will return error messages in message format.
     * 
     * @param error
     *            - contains error data.
     * @return - Formatted error message.
     */
    static String getErrorMessage(final ErrorInterface error) {
        return MessageFormat.format("{0}: {1}", error.getCode(), error.getMessage());
    }

    /**
     * This method will return error messages in message format along with
     * arguments to print.
     *
     * @param error
     *            The error interface.
     * @param arguments
     *            The list of argument to print along with error interface.
     * @return Error messages with error code, data & argument.
     */
    static String getErrorMessage(final ErrorInterface error, final Object... arguments) {
        return MessageFormat.format(getErrorMessage(error), arguments);
    }
}
