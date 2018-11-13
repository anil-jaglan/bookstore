package com.bookstore.rest.transferobjects;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

/**
 * 
 * Book base info transfer object.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public class BookBasicInfoTO extends ResourceSupport implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8296893386559196507L;

    /**
     * Book ISBN number.
     */
    @NotBlank(message = "ISBN can not be blank")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 to 13 characters")
    private String isbn;

    /**
     * Book title.
     */
    @NotBlank(message = "Title can not be blank")
    @Size(max = 255, message = "Title should not be more than 255 characters")
    private String title;

    /**
     * Book author name.
     */
    @NotBlank(message = "Author name can not be blank")
    @Size(max = 100, message = "Author name should not be more than 100 characters")
    private String author;

    /**
     * Book price.
     */
    @Min(value = 1, message = "Price can not be less than 1")
    @Positive(message = "Price value should be only in positive")
    private float price;

    /**
     * Total number of copies.
     */
    @Min(value = 1, message = "Price can not be less than 1")
    @Positive(message = "Total copies should be only in positive")
    private int totalCopies;

    /**
     * Book genre.
     */
    @NotBlank(message = "Genre can not be null")
    private String genre;

    /**
     * Book publish date.
     */
    @PastOrPresent(message = "Publish date should be past or today")
    private LocalDate publishDate;

    /**
     * Book description.
     */
    @Size(max = 500, message = "Description should not be more than 500 characters")
    private String desc;

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
     * Getter of {@link #genre}.
     * 
     * @return {@link #genre}
     */
    public String getGenre() {
        return genre;
    }

    /**
     * 
     * Setter of {@link #genre}.
     * 
     * @param genre
     *            to be set
     */
    public void setGenre(String genre) {
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
        final BookBasicInfoTO other = (BookBasicInfoTO) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BookBasicInfoTO [isbn=");
        builder.append(isbn);
        builder.append(", title=");
        builder.append(title);
        builder.append(", author=");
        builder.append(author);
        builder.append(", price=");
        builder.append(price);
        builder.append(", totalCopies=");
        builder.append(totalCopies);
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
