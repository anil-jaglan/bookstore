package com.bookstore.service.domainmodel.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Entity to store book purchase information.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Entity
@Table(name = "BOOKS_PURCHASED")
public class BookPurchaseEntity implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PURCHASE_ID", nullable = false)
    private Long purchaseId;

    @Column(name = "ISBN", length = 13, nullable = false)
    private String isbn;

    @Column(name = "PURCHASE_DATE", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "NO_OF_COPIES", nullable = false)
    private int noOfCopies;

    /**
     * 
     * Constructor.
     *
     */
    public BookPurchaseEntity() {
        super();
    }

    /**
     * 
     * Constructor.
     *
     * @param isbn
     * @param noOfCopies
     */
    public BookPurchaseEntity(String isbn, int noOfCopies) {
        super();
        this.isbn = isbn;
        this.purchaseDate = LocalDate.now();
        this.noOfCopies = noOfCopies;
    }

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
     * Getter of {@link #purchaseDate}.
     * 
     * @return {@link #purchaseDate}
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 
     * Setter of {@link #purchaseDate}.
     * 
     * @param purchaseDate
     *            to be set
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (purchaseId ^ (purchaseId >>> 32));
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
        final BookPurchaseEntity other = (BookPurchaseEntity) obj;
        return purchaseId != other.purchaseId ? false : true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BookPurchaseEntity [purchaseId=");
        builder.append(purchaseId);
        builder.append(", isbn=");
        builder.append(isbn);
        builder.append(", purchaseDate=");
        builder.append(purchaseDate);
        builder.append(", noOfCopies=");
        builder.append(noOfCopies);
        builder.append("]");
        return builder.toString();
    }

}
