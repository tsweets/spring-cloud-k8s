package org.beer30.springcloud.simpleprocessor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.simpleprocessor.domain.Transaction;
import org.beer30.springcloud.simpleprocessor.domain.dto.TransactionDTO;
import org.beer30.springcloud.simpleprocessor.domain.mapper.TransactionMapper;
import org.beer30.springcloud.simpleprocessor.repository.TransactionRepository;
import org.beer30.springcloud.simpleprocessor.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;


/**
 * Service Implementation for managing Transaction.
 */
@Service
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Inject
    private TransactionMapper transactionMapper;


    /**
     * Save a transaction.
     *
     * @return the persisted entity
     */
    public TransactionDTO save(TransactionDTO transactionDTO) {
        log.debug("Request to save Transaction : {}", transactionDTO);
        Transaction transaction = transactionMapper.transactionDTOToTransaction(transactionDTO);
        transaction = transactionRepository.save(transaction);
        TransactionDTO result = transactionMapper.transactionToTransactionDTO(transaction);
        return result;
    }

    /**
     * get all the transactions.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Transaction> findAll(Pageable pageable) {
        log.debug("Request to get all Transactions");
        Page<Transaction> result = transactionRepository.findAll(pageable);
        return result;
    }

    /**
     * get one transaction by id.
     *
     * @return the entity
     */
    @Transactional(readOnly = true)
    public TransactionDTO findOne(Long id) {
        log.debug("Request to get Transaction : {}", id);
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            return null;
        }
        TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction.get());
        return transactionDTO;
    }

    /**
     * delete the  transaction by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Transaction : {}", id);
        transactionRepository.deleteById(id);
    }


}
