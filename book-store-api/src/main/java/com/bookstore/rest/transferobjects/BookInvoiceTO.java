package com.bookstore.rest.transferobjects;

import java.io.Serializable;

/**
 * 
 * Transfer object to contains Book invoice information.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class BookInvoiceTO implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5202190664631190465L;

    /**
     * Unique invoice id.
     */
    private String invoiceId;

    /**
     * Purchased book information.
     */
    private BookBasicInfoTO item;

    /**
     * Total invoice value.
     */
    private float invoiceValue;

    /**
     * Book invoice date.
     */
    private String invoiceDate;

    /**
     * 
     * Getter of {@link #invoiceId}.
     * 
     * @return {@link #invoiceId}
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * 
     * Setter of {@link #invoiceId}.
     * 
     * @param invoiceId
     *            to be set
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * 
     * Getter of {@link #item}.
     * 
     * @return {@link #item}
     */
    public BookBasicInfoTO getItem() {
        return item;
    }

    /**
     * 
     * Setter of {@link #item}.
     * 
     * @param item
     *            to be set
     */
    public void setItem(BookBasicInfoTO item) {
        this.item = item;
    }

    /**
     * 
     * Getter of {@link #invoiceValue}.
     * 
     * @return {@link #invoiceValue}
     */
    public float getInvoiceValue() {
        return invoiceValue;
    }

    /**
     * 
     * Setter of {@link #invoiceValue}.
     * 
     * @param invoiceValue
     *            to be set
     */
    public void setInvoiceValue(float invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    /**
     * 
     * Getter of {@link #invoiceDate}.
     * 
     * @return {@link #invoiceDate}
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * 
     * Setter of {@link #invoiceDate}.
     * 
     * @param invoiceDate
     *            to be set
     */
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
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
        BookInvoiceTO other = (BookInvoiceTO) obj;
        if (invoiceId == null) {
            if (other.invoiceId != null)
                return false;
        } else if (!invoiceId.equals(other.invoiceId))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BookInvoiceTO [invoiceId="
                + invoiceId
                + ", item="
                + item
                + ", invoiceValue="
                + invoiceValue
                + ", invoiceDate="
                + invoiceDate
                + "]";
    }

}
