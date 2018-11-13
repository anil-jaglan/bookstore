package com.bookstore.common.errors;

import java.io.Serializable;

/**
 * 
 * Class to hold error data.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class ErrorData implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5289034106454120686L;

    /**
     * Error code.
     */
    private String code;

    /**
     * Error message.
     */
    private String message;

    /**
     * 
     * Constructor.
     *
     * @param code
     *            - Error code.
     * @param message
     *            - Error message.
     */
    public ErrorData(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 
     * Getter of {@link #code}.
     * 
     * @return {@link #code}
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * Getter of {@link #message}.
     * 
     * @return {@link #message}
     */
    public String getMessage() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ErrorData [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorData other = (ErrorData) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

}
