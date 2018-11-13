package com.bookstore.common.exceptions;

import com.bookstore.common.errors.ErrorInterface;

import java.io.Serializable;

/**
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class ExtendedExceptionData implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * get error message string from data along with its argument.
     *
     * @param data
     * @return
     */
    static String getErrorMessage(final ExtendedExceptionData data) {
        final StringBuilder builder = new StringBuilder();
        builder.append(ErrorInterface.getErrorMessage(data.getError(), data.getErrorArguments())).append("\n");

        return builder.toString();
    }

    /**
     *
     * get error message string from data.
     *
     * @param data
     * @return
     */
    static String getMessage(final ExtendedExceptionData data) {
        return ErrorInterface.getErrorMessage(data.getError());
    }

    /**
     * return stack trace as String.
     *
     * @param exception
     * @return
     */
    public static String getStackTraceAsString(final Throwable exception) {
        final StringBuilder stackString = new StringBuilder("STACK:").append("\n");

        final StackTraceElement[] stack = exception.getStackTrace();
        for (final StackTraceElement stackElem : stack) {
            stackString.append('\t').append(stackElem);
            stackString.append("\n");
        }
        return stackString.toString();
    }

    /**
     * ErrorCode.
     */
    private final ErrorInterface error;

    /**
     * Arguments incorporated into an error message.
     */
    private Object[] errorArguments = new String[]
        {};

    /**
     * Constructor.
     *
     * @param error
     *            providing further information about reason causing the error,
     *            see also {@link ErrorInterface}
     */
    ExtendedExceptionData(final ErrorInterface error) {
        super();
        this.error = error;
    }

    /**
     *
     * Constructor.
     *
     * @param error
     *            providing further information about reason causing the error,
     *            see also {@link ErrorInterface}
     * @param errorArguments
     *            {@link #_errorArguments}
     */
    ExtendedExceptionData(final ErrorInterface error, final Object... errorArguments) {
        this(error);
        setErrorArguments(errorArguments);
    }

    /**
     * Getter for {@link #_error}.
     *
     * @return {@link #_error}
     */
    final ErrorInterface getError() {
        return this.error;
    }

    /**
     * Getter for {@link #_errorArguments}.
     *
     * @return {@link #_errorArguments}
     */
    final Object[] getErrorArguments() {
        return this.errorArguments;
    }

    /**
     *
     * Gets error identifier as string.
     *
     * @return error identifier as string. Empty string if no error defined.
     */
    final String getErrorCode() {
        return getError().getCode();
    }

    /**
     *
     * Setter of {@link #_errorArguments}.
     *
     * @param errorArguments
     *            to be set
     */
    public final void setErrorArguments(final Object[] errorArguments) {
        if (errorArguments == null) {
            this.errorArguments = new String[]
                {};
        } else {
            this.errorArguments = errorArguments;
        }
    }

    @Override
    public String toString() {
        return getErrorMessage(this);
    }
}
