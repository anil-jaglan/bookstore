package com.bookstore.common.exceptions;

import com.bookstore.common.errors.ErrorData;
import com.bookstore.common.errors.ErrorInterface;

/**
 * 
 * Extension of RuntimException to format generic messages.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class ExtendedRuntimeException extends RuntimeException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2014437185118603573L;

    /**
     * Common data for ExtendedException and ExtendedRuntimeException.
     */
    private final ExtendedExceptionData exceptionContent;

    /**
     * Constructor.
     *
     * @param error
     *            providing further information about reason causing the error,
     *            see also {@link ErrorInterface}
     */
    public ExtendedRuntimeException(final ErrorInterface error) {
        super(error.getMessage());
        exceptionContent = new ExtendedExceptionData(error);
    }

    /**
     * Constructor.
     *
     * @param error
     *            providing further information about reason causing the error,
     *            see also {@link ErrorInterface}
     * @param errorArguments
     *            arguments for error message
     */
    public ExtendedRuntimeException(final ErrorInterface error, final Object... errorArguments) {

        super(error.getMessage());
        exceptionContent = new ExtendedExceptionData(error, errorArguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return ExtendedExceptionData.getMessage(exceptionContent);
    }

    /**
     * Gets the error message. This is the message text of the error with
     * placeholders replaced by the arguments given in the constructor of this.
     *
     * @return message or empty string, if no error exists.
     */
    public String getErrorMessage() {
        return ExtendedExceptionData.getErrorMessage(exceptionContent);

    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return new StringBuilder().append(getExceptionContent().toString()).append(
                ExtendedExceptionData.getStackTraceAsString(this)).toString();
    }

    /**
     * Getter of {@link #exceptionContent}.
     *
     * @return {@link #exceptionContent}
     */
    protected ExtendedExceptionData getExceptionContent() {
        return exceptionContent;
    }

    /**
     * Get ErrorInterface.
     *
     * @return {@link #ErrorInterface}
     */
    public ErrorData getErrorData() {
        return exceptionContent.getError().getErrorData();
    }
}
