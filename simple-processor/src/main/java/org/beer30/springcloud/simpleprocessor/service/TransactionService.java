package org.beer30.springcloud.simpleprocessor.service;

import org.beer30.springcloud.simpleprocessor.domain.Transaction;
import org.beer30.springcloud.simpleprocessor.domain.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Transaction.
 */
public interface TransactionService {

    /**
     * Save a transaction.
     *
     * @return the persisted entity
     */
    TransactionDTO save(TransactionDTO transactionDTO);

    /**
     * get all the transactions.
     *
     * @return the list of entities
     */
    Page<Transaction> findAll(Pageable pageable);

    /**
     * get the "id" transaction.
     *
     * @return the entity
     */
    TransactionDTO findOne(Long id);

    /**
     * delete the "id" transaction.
     */
    void delete(Long id);


}
