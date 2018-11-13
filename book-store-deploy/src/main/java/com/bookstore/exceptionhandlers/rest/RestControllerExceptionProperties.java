package com.bookstore.exceptionhandlers.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * Exception messages file to read from configured properties.
 *
 * @author Anil Jaglan
 * @version $Revision: 1.0 $
 */
@Configuration
@PropertySource("classpath:errors.properties")
@ConfigurationProperties("error")
public class RestControllerExceptionProperties {

    /**
     * Constant for <code>ERROR_MESSAGE_SYSTEM_TEXT</code>.
     */
    public static final String ERROR_MESSAGE_SYSTEM_TEXT = "system.text";

    /**
     * Constant for <code>ERROR_MESSAGE_MISSING_PARAMETER</code>.
     */
    public static final String ERROR_MESSAGE_MISSING_PARAMETER = "missing.parameter";

    /**
     * Constant for <code>ERROR_MESSAGE_MISMATCH_ARGUMENT_TYPE</code>.
     */
    public static final String ERROR_MESSAGE_MISMATCH_ARGUMENT_TYPE = "mismatch.argument.type";

    /**
     * Constant for <code>ERROR_MESSAGE_NO_METHOD_HANDLER</code>.
     */
    public static final String ERROR_MESSAGE_NO_METHOD_HANDLER = "no.method.handler";

    /**
     * Constant for <code>ERROR_MESSAGE_METHOD_NOT_SUPPORTED</code>.
     */
    public static final String ERROR_MESSAGE_METHOD_NOT_SUPPORTED = "method.not.supported";

    /** The error messages */
    private Map<String, String> message = new HashMap<>();

    /**
     * Sets the error messages.
     *
     * @param messages
     *            the error messages
     */
    public void setMessage(final Map<String, String> message) {
        this.message = message;
    }

    /**
     * Gets the error messages.
     *
     * @return the messages
     */
    public Map<String, String> getMessage() {
        return message;
    }

    /**
     * Get message according to a key.
     *
     * @param key
     *            the key.
     * @return String getMessage for key.
     */
    public String getMessage(final String key) {
        return message.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        for (final Map.Entry<String, String> entry : message.entrySet()) {
            builder.append("[").append(entry.getKey()).append("]").append("   ").append(entry.getValue()).append("\n");
        }

        return builder.toString();
    }
}
