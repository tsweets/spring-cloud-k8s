package org.beer30.springcloud.simpleprocessor.controller;

import org.beer30.springcloud.simpleprocessor.SimpleProcessorApplication;
import org.beer30.springcloud.simpleprocessor.TestUtil;
import org.beer30.springcloud.simpleprocessor.domain.Transaction;
import org.beer30.springcloud.simpleprocessor.domain.dto.TransactionDTO;
import org.beer30.springcloud.simpleprocessor.domain.enums.TransactionType;
import org.beer30.springcloud.simpleprocessor.domain.mapper.TransactionMapper;
import org.beer30.springcloud.simpleprocessor.repository.TransactionRepository;
import org.beer30.springcloud.simpleprocessor.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TransactionResource REST controller.
 *
 * @see Transaction
 */
@SpringBootTest(classes = SimpleProcessorApplication.class)
@WebAppConfiguration
public class TransactionResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final Integer DEFAULT_ENV_ID = 1;
    private static final Integer UPDATED_ENV_ID = 2;


    private static final TransactionType DEFAULT_TYPE = TransactionType.UNKNOWN;
    private static final TransactionType UPDATED_TYPE = TransactionType.PURCHASE_PIN;

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_DATE_STR = dateTimeFormatter.format(DEFAULT_DATE);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);
    private static final String DEFAULT_NOTE = "AAAAA";
    private static final String UPDATED_NOTE = "BBBBB";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTransactionMockMvc;

    private Transaction transaction;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TransactionController transactionController = new TransactionController();
        ReflectionTestUtils.setField(transactionController, "transactionService", transactionService);
        ReflectionTestUtils.setField(transactionController, "transactionMapper", transactionMapper);
        this.restTransactionMockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    @BeforeEach
    public void initTest() {
        transaction = new Transaction();
        transaction.setEnvId(DEFAULT_ENV_ID);
        transaction.setType(DEFAULT_TYPE);
        transaction.setDate(DEFAULT_DATE);
        transaction.setAmount(DEFAULT_AMOUNT);
        transaction.setNote(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createTransaction() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction);

        restTransactionMockMvc.perform(post("/processor/transactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(transactionDTO)))
                .andExpect(status().isCreated());

        // Validate the Transaction in the database
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions).hasSize(databaseSizeBeforeCreate + 1);
        Transaction testTransaction = transactions.get(transactions.size() - 1);
        assertThat(testTransaction.getEnvId()).isEqualTo(DEFAULT_ENV_ID);
        assertThat(testTransaction.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTransaction.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testTransaction.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void checkEnvIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setEnvId(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction);

        restTransactionMockMvc.perform(post("/processor/transactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(transactionDTO)))
                .andExpect(status().isBadRequest());

        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setType(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction);

        restTransactionMockMvc.perform(post("/processor/transactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(transactionDTO)))
                .andExpect(status().isBadRequest());

        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setDate(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction);

        restTransactionMockMvc.perform(post("/processor/transactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(transactionDTO)))
                .andExpect(status().isBadRequest());

        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactions() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get all the transactions
        restTransactionMockMvc.perform(get("/processor/transactions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(transaction.getId().intValue())))
                .andExpect(jsonPath("$.[*].envId").value(hasItem(DEFAULT_ENV_ID)))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
                //       .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE_STR)))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
                .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }

    @Test
    @Transactional
    public void getTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get the transaction
        restTransactionMockMvc.perform(get("/processor/transactions/{id}", transaction.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(transaction.getId().intValue()))
                .andExpect(jsonPath("$.envId").value(DEFAULT_ENV_ID))
                .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
                //    .andExpect(jsonPath("$.date").value(DEFAULT_DATE_STR))
                .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
                .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    public void getNonExistingTransaction() throws Exception {
        // Get the transaction
        restTransactionMockMvc.perform(get("/processor/transactions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Update the transaction
        transaction.setEnvId(UPDATED_ENV_ID);
        transaction.setType(UPDATED_TYPE);
        transaction.setDate(UPDATED_DATE);
        transaction.setAmount(UPDATED_AMOUNT);
        transaction.setNote(UPDATED_NOTE);
        TransactionDTO transactionDTO = transactionMapper.transactionToTransactionDTO(transaction);

        restTransactionMockMvc.perform(put("/processor/transactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(transactionDTO)))
                .andExpect(status().isOk());

        // Validate the Transaction in the database
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions).hasSize(databaseSizeBeforeUpdate);
        Transaction testTransaction = transactions.get(transactions.size() - 1);
        assertThat(testTransaction.getEnvId()).isEqualTo(UPDATED_ENV_ID);
        assertThat(testTransaction.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTransaction.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testTransaction.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void deleteTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeDelete = transactionRepository.findAll().size();

        // Get the transaction
        restTransactionMockMvc.perform(delete("/processor/transactions/{id}", transaction.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
