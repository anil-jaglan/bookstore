package com.bookstore.service.domainmodel.entity;

import com.bookstore.common.enums.GenreEnum;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 
 * Entity class to persist book data into BookDataStore.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Entity
@Table(name = "BOOK_STORE")
public class BookEntity implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private Long bookId;

    /**
     * ISBN number.
     */
    @Column(name = "ISBN", length = 13, nullable = false)
    private String isbn;

    /**
     * Title of the book.
     */
    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;

    /**
     * Author of the book.
     */
    @Column(name = "AUTHOR", length = 100, nullable = false)
    private String author;

    /**
     * Book price.
     */
    @Column(name = "PRICE", nullable = false)
    private float price;

    /**
     * Total number of copies initially.
     */
    @Column(name = "TOTAL_COPIES", nullable = false)
    private int totalCopies;

    /**
     * Number of copies sold.
     */
    @Column(name = "SOLD_COPIES", nullable = false)
    private int soldCopies;

    /**
     * Book genre.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private GenreEnum genre;

    /**
     * Book publish date.
     */
    @Column(name = "PUBLISH_DATE")
    private LocalDate publishDate;

    /**
     * Short description about book.
     */
    @Column(name = "DESCRIPTION", length = 500)
    private String desc;

    /**
     * Entity version for enabling optimistic locking.
     */
    @Version
    @Column(name = "VERSION")
    private Integer version;

    /**
     * 
     * Getter of {@link #bookId}.
     * 
     * @return {@link #bookId}
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 
     * Setter of {@link #bookId}.
     * 
     * @param bookId
     *            to be set
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
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
     * Getter of {@link #author}.
     * 
     * @return {@link #author}
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * Setter of {@link #author}.
     * 
     * @param author
     *            to be set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * Getter of {@link #price}.
     * 
     * @return {@link #price}
     */
    public float getPrice() {
        return price;
    }

    /**
     * 
     * Setter of {@link #price}.
     * 
     * @param price
     *            to be set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * 
     * Getter of {@link #totalCopies}.
     * 
     * @return {@link #totalCopies}
     */
    public int getTotalCopies() {
        return totalCopies;
    }

    /**
     * 
     * Setter of {@link #totalCopies}.
     * 
     * @param totalCopies
     *            to be set
     */
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    /**
     * 
     * Getter of {@link #soldCopies}.
     * 
     * @return {@link #soldCopies}
     */
    public int getSoldCopies() {
        return soldCopies;
    }

    /**
     * 
     * Setter of {@link #soldCopies}.
     * 
     * @param soldCopies
     *            to be set
     */
    public void setSoldCopies(int soldCopies) {
        this.soldCopies = soldCopies;
    }

    /**
     * 
     * Getter of {@link #genre}.
     * 
     * @return {@link #genre}
     */
    public GenreEnum getGenre() {
        return genre;
    }

    /**
     * 
     * Setter of {@link #genre}.
     * 
     * @param genre
     *            to be set
     */
    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    /**
     * 
     * Getter of {@link #publishDate}.
     * 
     * @return {@link #publishDate}
     */
    public LocalDate getPublishDate() {
        return publishDate;
    }

    /**
     * 
     * Setter of {@link #publishDate}.
     * 
     * @param publishDate
     *            to be set
     */
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 
     * Getter of {@link #desc}.
     * 
     * @return {@link #desc}
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * Setter of {@link #desc}.
     * 
     * @param desc
     *            to be set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 
     * Getter of {@link #version}.
     * 
     * @return {@link #version}
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 
     * Setter of {@link #version}.
     * 
     * @param version
     *            to be set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
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
        BookEntity other = (BookEntity) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BookEntity [bookId=");
        builder.append(bookId);
        builder.append(", isbn=");
        builder.append(isbn);
        builder.append(", title=");
        builder.append(title);
        builder.append(", author=");
        builder.append(author);
        builder.append(", price=");
        builder.append(price);
        builder.append(", totalCopies=");
        builder.append(totalCopies);
        builder.append(", soldCopies=");
        builder.append(soldCopies);
        builder.append(", genre=");
        builder.append(genre);
        builder.append(", publishDate=");
        builder.append(publishDate);
        builder.append(", desc=");
        builder.append(desc);
        builder.append("]");
        return builder.toString();
    }

}
