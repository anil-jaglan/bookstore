package com.bookstore.service.businessobjects;

import java.io.Serializable;

/**
 * 
 * Business object for post data.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class PostBO implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * User id.
     */
    private int userId;

    /**
     * Post id.
     */
    private int id;

    /**
     * Post title.
     */
    private String title;

    /**
     * Post body.
     */
    private String body;

    /**
     * 
     * Getter of {@link #userId}.
     * 
     * @return {@link #userId}
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 
     * Setter of {@link #userId}.
     * 
     * @param userId
     *            to be set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 
     * Getter of {@link #id}.
     * 
     * @return {@link #id}
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * Setter of {@link #id}.
     * 
     * @param id
     *            to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * Getter of {@link #title}.
     * 
     * @return {@link #title}
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * Setter of {@link #title}.
     * 
     * @param title
     *            to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * Getter of {@link #body}.
     * 
     * @return {@link #body}
     */
    public String getBody() {
        return body;
    }

    /**
     * 
     * Setter of {@link #body}.
     * 
     * @param body
     *            to be set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        PostBO other = (PostBO) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PostBO [userId=");
        builder.append(userId);
        builder.append(", id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", body=");
        builder.append(body);
        builder.append("]");
        return builder.toString();
    }

}
