package com.bookstore.rest.transferobjects;

import java.io.Serializable;

/**
 * 
 * Book buying request transfer object.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class BuyBookRequestTO implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -825705449667307789L;

    /**
     * Purchase Id.
     */
    private Long purchaseId;

    /**
     * Book ISBN number to be purchased.
     */
    private String isbn;

    /**
     * No. of copies to be purchased.
     */
    private int noOfCopies;

    /**
     * 
     * Getter of {@link #purchaseId}.
     * 
     * @return {@link #purchaseId}
     */
    public Long getPurchaseId() {
        return purchaseId;
    }

    /**
     * 
     * Setter of {@link #purchaseId}.
     * 
     * @param purchaseId
     *            to be set
     */
    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * 
     * Getter of {@link #isbn}.
     * 
     * @return {@link #isbn}
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * 
     * Setter of {@link #isbn}.
     * 
     * @param isbn
     *            to be set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 
     * Getter of {@link #noOfCopies}.
     * 
     * @return {@link #noOfCopies}
     */
    public int getNoOfCopies() {
        return noOfCopies;
    }

    /**
     * 
     * Setter of {@link #noOfCopies}.
     * 
     * @param noOfCopies
     *            to be set
     */
    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BuyBookTO [isbn=");
        builder.append(isbn);
        builder.append(", purchaseId=");
        builder.append(purchaseId);
        builder.append(", noOfCopies=");
        builder.append(noOfCopies);
        builder.append("]");
        return builder.toString();
    }

}
