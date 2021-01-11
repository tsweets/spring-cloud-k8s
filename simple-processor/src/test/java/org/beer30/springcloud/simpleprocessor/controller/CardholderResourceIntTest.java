package org.beer30.springcloud.simpleprocessor.controller;

import org.beer30.springcloud.simpleprocessor.SimpleProcessorApplication;
import org.beer30.springcloud.simpleprocessor.TestUtil;
import org.beer30.springcloud.simpleprocessor.domain.Cardholder;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardholderDTO;
import org.beer30.springcloud.simpleprocessor.domain.mapper.CardholderMapper;
import org.beer30.springcloud.simpleprocessor.repository.CardholderRepository;
import org.beer30.springcloud.simpleprocessor.service.CardholderService;
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
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.beer30.springcloud.simpleprocessor.TestUtil.getJsonFromObject;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the Cardholder REST controller.
 *
 * @see Cardholder
 */

@SpringBootTest(classes = SimpleProcessorApplication.class)
@WebAppConfiguration
public class CardholderResourceIntTest {


    private static final Integer DEFAULT_ENV_ID = 1;
    private static final Integer UPDATED_ENV_ID = 2;

    private static final Long DEFAULT_EXTERNAL_ID = 1L;
    private static final Long UPDATED_EXTERNAL_ID = 2L;
    private static final String DEFAULT_FIRST_NAME = "AAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBB";
    private static final String DEFAULT_MIDDLE_NAME = "AAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBB";
    private static final String DEFAULT_LAST_NAME = "AAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBB";

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_SSN = "AAAAA";
    private static final String UPDATED_SSN = "BBBBB";
    private static final String DEFAULT_HOME_STREET1 = "AAAAA";
    private static final String UPDATED_HOME_STREET1 = "BBBBB";
    private static final String DEFAULT_HOME_STREET2 = "AAAAA";
    private static final String UPDATED_HOME_STREET2 = "BBBBB";
    private static final String DEFAULT_HOME_CITY = "AAAAA";
    private static final String UPDATED_HOME_CITY = "BBBBB";
    private static final String DEFAULT_HOME_STATE = "AAAAA";
    private static final String UPDATED_HOME_STATE = "BBBBB";
    private static final String DEFAULT_HOME_POSTAL_CODE = "AAAAA";
    private static final String UPDATED_HOME_POSTAL_CODE = "BBBBB";
    private static final String DEFAULT_SHIP_STREET1 = "AAAAA";
    private static final String UPDATED_SHIP_STREET1 = "BBBBB";
    private static final String DEFAULT_SHIP_STREET2 = "AAAAA";
    private static final String UPDATED_SHIP_STREET2 = "BBBBB";
    private static final String DEFAULT_SHIP_CITY = "AAAAA";
    private static final String UPDATED_SHIP_CITY = "BBBBB";
    private static final String DEFAULT_SHIP_STATE = "AAAAA";
    private static final String UPDATED_SHIP_STATE = "BBBBB";
    private static final String DEFAULT_SHIP_POSTAL_CODE = "AAAAA";
    private static final String UPDATED_SHIP_POSTAL_CODE = "BBBBB";
    private static final String DEFAULT_PHONE_NUMBER = "AAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBB";
    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";

    @Autowired
    private CardholderRepository cardholderRepository;

    @Autowired
    private CardholderMapper cardholderMapper;

    @Autowired
    private CardholderService cardholderService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCardholderMockMvc;

    private Cardholder cardholder;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CardholderController cardholderController = new CardholderController();
        ReflectionTestUtils.setField(cardholderController, "cardholderService", cardholderService);
        ReflectionTestUtils.setField(cardholderController, "cardholderMapper", cardholderMapper);
        this.restCardholderMockMvc = MockMvcBuilders.standaloneSetup(cardholderController)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    @BeforeEach
    public void initTest() {
        cardholder = new Cardholder();
        cardholder.setEnvId(DEFAULT_ENV_ID);
        cardholder.setExternalId(DEFAULT_EXTERNAL_ID);
        cardholder.setFirstName(DEFAULT_FIRST_NAME);
        cardholder.setMiddleName(DEFAULT_MIDDLE_NAME);
        cardholder.setLastName(DEFAULT_LAST_NAME);
        //     cardholder.setDob(DEFAULT_DOB);
        cardholder.setSsn(DEFAULT_SSN);
        cardholder.setHomeStreet1(DEFAULT_HOME_STREET1);
        cardholder.setHomeStreet2(DEFAULT_HOME_STREET2);
        cardholder.setHomeCity(DEFAULT_HOME_CITY);
        cardholder.setHomeState(DEFAULT_HOME_STATE);
        cardholder.setHomePostalCode(DEFAULT_HOME_POSTAL_CODE);
        cardholder.setShipStreet1(DEFAULT_SHIP_STREET1);
        cardholder.setShipStreet2(DEFAULT_SHIP_STREET2);
        cardholder.setShipCity(DEFAULT_SHIP_CITY);
        cardholder.setShipState(DEFAULT_SHIP_STATE);
        cardholder.setShipPostalCode(DEFAULT_SHIP_POSTAL_CODE);
        cardholder.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        cardholder.setEmail(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createCardholder() throws Exception {
        int databaseSizeBeforeCreate = cardholderRepository.findAll().size();

        // Create the Cardholder
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder);

        String requestJson = getJsonFromObject(cardholderDTO);


        restCardholderMockMvc.perform(post("/processor/cardholders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated());

        // Validate the Cardholder in the database
        List<Cardholder> cardholders = cardholderRepository.findAll();
        assertThat(cardholders).hasSize(databaseSizeBeforeCreate + 1);
        Cardholder testCardholder = cardholders.get(cardholders.size() - 1);
        assertThat(testCardholder.getEnvId()).isEqualTo(DEFAULT_ENV_ID);
        assertThat(testCardholder.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testCardholder.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCardholder.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testCardholder.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        // assertThat(testCardholder.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testCardholder.getSsn()).isEqualTo(DEFAULT_SSN);
        assertThat(testCardholder.getHomeStreet1()).isEqualTo(DEFAULT_HOME_STREET1);
        assertThat(testCardholder.getHomeStreet2()).isEqualTo(DEFAULT_HOME_STREET2);
        assertThat(testCardholder.getHomeCity()).isEqualTo(DEFAULT_HOME_CITY);
        assertThat(testCardholder.getHomeState()).isEqualTo(DEFAULT_HOME_STATE);
        assertThat(testCardholder.getHomePostalCode()).isEqualTo(DEFAULT_HOME_POSTAL_CODE);
        assertThat(testCardholder.getShipStreet1()).isEqualTo(DEFAULT_SHIP_STREET1);
        assertThat(testCardholder.getShipStreet2()).isEqualTo(DEFAULT_SHIP_STREET2);
        assertThat(testCardholder.getShipCity()).isEqualTo(DEFAULT_SHIP_CITY);
        assertThat(testCardholder.getShipState()).isEqualTo(DEFAULT_SHIP_STATE);
        assertThat(testCardholder.getShipPostalCode()).isEqualTo(DEFAULT_SHIP_POSTAL_CODE);
        assertThat(testCardholder.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testCardholder.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }


    @Test
    @Transactional
    public void checkEnvIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardholderRepository.findAll().size();
        // set the field null
        cardholder.setEnvId(null);

        // Create the Cardholder, which fails.
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder);

        restCardholderMockMvc.perform(post("/processor/cardholders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(getJsonFromObject(cardholderDTO)))
                .andExpect(status().isBadRequest());

        List<Cardholder> cardholders = cardholderRepository.findAll();
        assertThat(cardholders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExternalIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardholderRepository.findAll().size();
        // set the field null
        cardholder.setExternalId(null);

        // Create the Cardholder, which fails.
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder);

        restCardholderMockMvc.perform(post("/processor/cardholders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(cardholderDTO)))
                .andExpect(status().isBadRequest());

        List<Cardholder> cardholders = cardholderRepository.findAll();
        assertThat(cardholders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardholderRepository.findAll().size();
        // set the field null
        cardholder.setFirstName(null);

        // Create the Cardholder, which fails.
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder);

        restCardholderMockMvc.perform(post("/processor/cardholders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(cardholderDTO)))
                .andExpect(status().isBadRequest());

        List<Cardholder> cardholders = cardholderRepository.findAll();
        assertThat(cardholders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cardholderRepository.findAll().size();
        // set the field null
        cardholder.setLastName(null);

        // Create the Cardholder, which fails.
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder);

        restCardholderMockMvc.perform(post("/processor/cardholders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(cardholderDTO)))
                .andExpect(status().isBadRequest());

        List<Cardholder> cardholders = cardholderRepository.findAll();
        assertThat(cardholders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCardholders() throws Exception {
        // Initialize the database
        cardholderRepository.saveAndFlush(cardholder);

        // Get all the cardholders
        restCardholderMockMvc.perform(get("/processor/cardholders?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cardholder.getId().intValue())))
                .andExpect(jsonPath("$.[*].envId").value(hasItem(DEFAULT_ENV_ID)))
                .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID.intValue())))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
                .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
                .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
                //     .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
                .andExpect(jsonPath("$.[*].ssn").value(hasItem(DEFAULT_SSN)))
                .andExpect(jsonPath("$.[*].homeStreet1").value(hasItem(DEFAULT_HOME_STREET1)))
                .andExpect(jsonPath("$.[*].homeStreet2").value(hasItem(DEFAULT_HOME_STREET2)))
                .andExpect(jsonPath("$.[*].homeCity").value(hasItem(DEFAULT_HOME_CITY)))
                .andExpect(jsonPath("$.[*].homeState").value(hasItem(DEFAULT_HOME_STATE)))
                .andExpect(jsonPath("$.[*].homePostalCode").value(hasItem(DEFAULT_HOME_POSTAL_CODE)))
                .andExpect(jsonPath("$.[*].shipStreet1").value(hasItem(DEFAULT_SHIP_STREET1)))
                .andExpect(jsonPath("$.[*].shipStreet2").value(hasItem(DEFAULT_SHIP_STREET2)))
                .andExpect(jsonPath("$.[*].shipCity").value(hasItem(DEFAULT_SHIP_CITY)))
                .andExpect(jsonPath("$.[*].shipState").value(hasItem(DEFAULT_SHIP_STATE)))
                .andExpect(jsonPath("$.[*].shipPostalCode").value(hasItem(DEFAULT_SHIP_POSTAL_CODE)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    public void getCardholder() throws Exception {
        // Initialize the database
        cardholderRepository.saveAndFlush(cardholder);

        // Get the cardholder
        restCardholderMockMvc.perform(get("/processor/cardholders/{id}", cardholder.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(cardholder.getId().intValue()))
                .andExpect(jsonPath("$.envId").value(DEFAULT_ENV_ID))
                .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID.intValue()))
                .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
                .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
                .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
                //       .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
                .andExpect(jsonPath("$.ssn").value(DEFAULT_SSN))
                .andExpect(jsonPath("$.homeStreet1").value(DEFAULT_HOME_STREET1))
                .andExpect(jsonPath("$.homeStreet2").value(DEFAULT_HOME_STREET2))
                .andExpect(jsonPath("$.homeCity").value(DEFAULT_HOME_CITY))
                .andExpect(jsonPath("$.homeState").value(DEFAULT_HOME_STATE))
                .andExpect(jsonPath("$.homePostalCode").value(DEFAULT_HOME_POSTAL_CODE))
                .andExpect(jsonPath("$.shipStreet1").value(DEFAULT_SHIP_STREET1))
                .andExpect(jsonPath("$.shipStreet2").value(DEFAULT_SHIP_STREET2))
                .andExpect(jsonPath("$.shipCity").value(DEFAULT_SHIP_CITY))
                .andExpect(jsonPath("$.shipState").value(DEFAULT_SHIP_STATE))
                .andExpect(jsonPath("$.shipPostalCode").value(DEFAULT_SHIP_POSTAL_CODE))
                .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
                .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    public void getNonExistingCardholder() throws Exception {
        // Get the cardholder
        restCardholderMockMvc.perform(get("/processor/cardholders/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCardholder() throws Exception {
        // Initialize the database
        cardholderRepository.saveAndFlush(cardholder);

        int databaseSizeBeforeUpdate = cardholderRepository.findAll().size();

        // Update the cardholder
        cardholder.setEnvId(UPDATED_ENV_ID);
        cardholder.setExternalId(UPDATED_EXTERNAL_ID);
        cardholder.setFirstName(UPDATED_FIRST_NAME);
        cardholder.setMiddleName(UPDATED_MIDDLE_NAME);
        cardholder.setLastName(UPDATED_LAST_NAME);
        //   cardholder.setDob(UPDATED_DOB);
        cardholder.setSsn(UPDATED_SSN);
        cardholder.setHomeStreet1(UPDATED_HOME_STREET1);
        cardholder.setHomeStreet2(UPDATED_HOME_STREET2);
        cardholder.setHomeCity(UPDATED_HOME_CITY);
        cardholder.setHomeState(UPDATED_HOME_STATE);
        cardholder.setHomePostalCode(UPDATED_HOME_POSTAL_CODE);
        cardholder.setShipStreet1(UPDATED_SHIP_STREET1);
        cardholder.setShipStreet2(UPDATED_SHIP_STREET2);
        cardholder.setShipCity(UPDATED_SHIP_CITY);
        cardholder.setShipState(UPDATED_SHIP_STATE);
        cardholder.setShipPostalCode(UPDATED_SHIP_POSTAL_CODE);
        cardholder.setPhoneNumber(UPDATED_PHONE_NUMBER);
        cardholder.setEmail(UPDATED_EMAIL);
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder);

        restCardholderMockMvc.perform(put("/processor/cardholders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.getJsonFromObject(cardholderDTO)))
                .andExpect(status().isOk());

        // Validate the Cardholder in the database
        List<Cardholder> cardholders = cardholderRepository.findAll();
        assertThat(cardholders).hasSize(databaseSizeBeforeUpdate);
        Cardholder testCardholder = cardholders.get(cardholders.size() - 1);
        assertThat(testCardholder.getEnvId()).isEqualTo(UPDATED_ENV_ID);
        assertThat(testCardholder.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testCardholder.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCardholder.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testCardholder.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        //   assertThat(testCardholder.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testCardholder.getSsn()).isEqualTo(UPDATED_SSN);
        assertThat(testCardholder.getHomeStreet1()).isEqualTo(UPDATED_HOME_STREET1);
        assertThat(testCardholder.getHomeStreet2()).isEqualTo(UPDATED_HOME_STREET2);
        assertThat(testCardholder.getHomeCity()).isEqualTo(UPDATED_HOME_CITY);
        assertThat(testCardholder.getHomeState()).isEqualTo(UPDATED_HOME_STATE);
        assertThat(testCardholder.getHomePostalCode()).isEqualTo(UPDATED_HOME_POSTAL_CODE);
        assertThat(testCardholder.getShipStreet1()).isEqualTo(UPDATED_SHIP_STREET1);
        assertThat(testCardholder.getShipStreet2()).isEqualTo(UPDATED_SHIP_STREET2);
        assertThat(testCardholder.getShipCity()).isEqualTo(UPDATED_SHIP_CITY);
        assertThat(testCardholder.getShipState()).isEqualTo(UPDATED_SHIP_STATE);
        assertThat(testCardholder.getShipPostalCode()).isEqualTo(UPDATED_SHIP_POSTAL_CODE);
        assertThat(testCardholder.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testCardholder.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void deleteCardholder() throws Exception {
        // Initialize the database
        cardholderRepository.saveAndFlush(cardholder);

        int databaseSizeBeforeDelete = cardholderRepository.findAll().size();

        // Get the cardholder
        restCardholderMockMvc.perform(delete("/processor/cardholders/{id}", cardholder.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Cardholder> cardholders = cardholderRepository.findAll();
        assertThat(cardholders).hasSize(databaseSizeBeforeDelete - 1);
    }
}
