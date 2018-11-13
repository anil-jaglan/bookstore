package com.bookstore.service.domainmodel.repository;

import com.bookstore.service.domainmodel.entity.BookPurchaseEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * BookStore repository interface to persist/retrieve book purchase entries.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@Repository
public interface BookPurchaseRepository extends CrudRepository<BookPurchaseEntity, Long> {

}
