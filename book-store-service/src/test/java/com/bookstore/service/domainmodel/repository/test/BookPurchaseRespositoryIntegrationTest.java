package com.bookstore.service.domainmodel.repository.test;

import com.bookstore.service.domainmodel.entity.BookPurchaseEntity;
import com.bookstore.service.domainmodel.repository.BookPurchaseRepository;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 
 * Class to test BookPurchaseRepository.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@EnableTransactionManagement
public class BookPurchaseRespositoryIntegrationTest {

    /**
     * Entity manager to persist data.
     */
    @Autowired
    private TestEntityManager entityManager;

    /**
     * Repository instance.
     */
    @Autowired
    private BookPurchaseRepository purchaseRepo;

    /**
     * 
     * Method to test .
     *
     */
    @Test
    public void testBookPurchaseEntry() {

        final BookPurchaseEntity purchasedBook = entityManager.persist(mockBookEntity());
        entityManager.flush();
        final Optional<BookPurchaseEntity> book = purchaseRepo.findById(purchasedBook.getPurchaseId());

        assertTrue(book.isPresent());
        assertEquals(5, book.get().getNoOfCopies());

    }

    private BookPurchaseEntity mockBookEntity() {
        return new BookPurchaseEntity("ISBN0001", 5);
    }
}
