package org.beer30.springcloud.simpleprocessor.controller;

import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.simpleprocessor.domain.Transaction;
import org.beer30.springcloud.simpleprocessor.domain.dto.TransactionDTO;
import org.beer30.springcloud.simpleprocessor.domain.mapper.TransactionMapper;
import org.beer30.springcloud.simpleprocessor.exception.TransactionExistsException;
import org.beer30.springcloud.simpleprocessor.service.TransactionService;
import org.beer30.springcloud.simpleprocessor.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * REST controller for managing Transaction.
 */
@Slf4j
@RestController
@RequestMapping("/processor")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;

    /**
     * POST  /transactions -> Create a new transaction.
     */
    @RequestMapping(value = "/transactions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) throws URISyntaxException {
        log.debug("REST request to save Transaction : {}", transactionDTO);
        if (transactionDTO.getId() != null) {
            throw new TransactionExistsException("transaction:idexists:A new transaction cannot already have an ID");
        }
        TransactionDTO result = transactionService.save(transactionDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * PUT  /transactions -> Updates an existing transaction.
     */
    @RequestMapping(value = "/transactions",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> updateTransaction(@Valid @RequestBody TransactionDTO transactionDTO) throws URISyntaxException {
        log.debug("REST request to update Transaction : {}", transactionDTO);
        if (transactionDTO.getId() == null) {
            return createTransaction(transactionDTO);
        }
        TransactionDTO result = transactionService.save(transactionDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET  /transactions -> get all the transactions.
     */
    @RequestMapping(value = "/transactions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(Pageable pageable)
            throws URISyntaxException {
        log.debug("REST request to get a page of Transactions");
        Page<Transaction> page = transactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/processor/transactions");
        return new ResponseEntity<>(page.getContent().stream()
                .map(transactionMapper::transactionToTransactionDTO)
                .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /transactions/:id -> get the "id" transaction.
     */
    @RequestMapping(value = "/transactions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {
        log.debug("REST request to get Transaction : {}", id);
        TransactionDTO transactionDTO = transactionService.findOne(id);
        return Optional.ofNullable(transactionDTO)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /transactions/:id -> delete the "id" transaction.
     */
    @RequestMapping(value = "/transactions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        log.debug("REST request to delete Transaction : {}", id);
        transactionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
