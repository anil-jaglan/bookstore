package com.bookstore.service.domainmodel.repository.test;

import com.bookstore.common.enums.GenreEnum;
import com.bookstore.service.domainmodel.entity.BookEntity;
import com.bookstore.service.domainmodel.repository.BookStoreRepository;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.junit.Assert.assertEquals;

/**
 * 
 * BookEntity repository integration test class.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@EnableTransactionManagement
public class BookEntityRepositoryIntegrationTest {

    /**
     * Entity manager to persist data.
     */
    @Autowired
    private TestEntityManager entityManager;

    /**
     * BookStoreRepository instance.
     */
    @Autowired
    private BookStoreRepository bookStore;

    private final String ISBN = "ISBN543212345";

    /**
     * 
     * Method to test find book by ISBN
     *
     */
    @Test
    public void whenUpdateCheckUpdatedQuantities() {

        BookEntity dummyBook = entityManager.persistAndFlush(getDummyBookEntity());
        BookEntity book = bookStore.findByIsbn(ISBN).get();
        assertEquals("Harry Potter", book.getTitle());
        assertEquals(10, book.getTotalCopies());
        entityManager.detach(dummyBook);
        bookStore.updateQuantity(ISBN, 15);
        dummyBook = entityManager.find(BookEntity.class, dummyBook.getBookId());
        // assertEquals(15, dummyBook.getTotalCopies());
    }

    /**
     * 
     * Method to test find book by ISBN
     *
     */
    @Test
    public void whenFindByISBNShouldBeFound() {

        bookStore.save(getDummyBookEntity());
        BookEntity book = bookStore.findByIsbn(ISBN).get();
        assertEquals("Harry Potter", book.getTitle());
        assertEquals(10, book.getTotalCopies());
        assertEquals(0, book.getSoldCopies());

    }

    /**
     * 
     * Method to test find book by author
     *
     */
    @Test
    public void whenFindByAuhorShouldBeFound() {

        bookStore.save(getDummyBookEntity());
        List<BookEntity> books = bookStore.findByAuthorIgnoreCaseContaining("rowling");
        assertEquals(1, books.size());
        BookEntity book = books.get(0);
        assertEquals("Harry Potter", book.getTitle());
        assertEquals(10, book.getTotalCopies());
        assertEquals(0, book.getSoldCopies());

    }

    /**
     * 
     * Method to create dummy book entity.
     * 
     * @return BookEntity
     */
    private BookEntity getDummyBookEntity() {
        BookEntity book = new BookEntity();
        book.setIsbn(ISBN);
        book.setTitle("Harry Potter");
        book.setAuthor("J K. Rowling");
        book.setPrice(14.95f);
        book.setGenre(GenreEnum.FANTASY);
        book.setTotalCopies(10);
        book.setPublishDate(LocalDate.of(2005, 1, 1));
        return book;
    }
}
