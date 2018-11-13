package com.bookstore.rest.transferobjects;

import java.io.Serializable;
import java.util.Map;
/**
 * 
 * Generic responesTO object.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class ResponseTO implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7561699883691317533L;

    /**
     * Response data.
     */
    private Object data;

    /**
     * contains any error.
     */
    private boolean error;

    /**
     * Error messages map.
     */
    private Map<Object, Object> errorMessages;

    /**
     * 
     * Constructor.
     *
     */
    protected ResponseTO() {
        super();
    }

    /**
     * 
     * Constructor.
     *
     * @param data
     */
    public ResponseTO(Object data) {
        super();
        this.data = data;
    }

    /**
     * 
     * Constructor.
     *
     * @param data
     * @param errorMessages
     */
    public ResponseTO(Object data, Map<Object, Object> errorMessages) {
        super();
        this.data = data;
        this.errorMessages = errorMessages;
    }

    /**
     * 
     * Getter of {@link #data}.
     * 
     * @return {@link #data}
     */
    public Object getData() {
        return data;
    }

    /**
     * 
     * Setter of {@link #data}.
     * 
     * @param data
     *            to be set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 
     * Getter of {@link #error}.
     * 
     * @return {@link #error}
     */
    public boolean isError() {
        return error;
    }

    /**
     * 
     * Getter of {@link #errorMessages}.
     * 
     * @return {@link #errorMessages}
     */
    public Map<Object, Object> getErrorMessages() {
        return errorMessages;
    }

    /**
     * 
     * Setter of {@link #errorMessages}.
     * 
     * @param errorMessages
     *            to be set
     */
    public void setErrorMessages(Map<Object, Object> errorMessages) {
        if (errorMessages != null && !errorMessages.isEmpty()) {
        }
        this.errorMessages = errorMessages;
    }

}
