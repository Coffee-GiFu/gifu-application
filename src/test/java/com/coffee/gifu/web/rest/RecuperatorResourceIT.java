package com.coffee.gifu.web.rest;

import com.coffee.gifu.GifuApp;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.domain.Organisation;
import com.coffee.gifu.repository.RecuperatorRepository;
import com.coffee.gifu.service.OrganisationService;
import com.coffee.gifu.service.RecuperatorService;
import com.coffee.gifu.service.UserService;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.mapper.RecuperatorMapper;
import com.coffee.gifu.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.coffee.gifu.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RecuperatorResource} REST controller.
 */
@SpringBootTest(classes = GifuApp.class)
public class RecuperatorResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private RecuperatorRepository recuperatorRepository;

    @Autowired
    private RecuperatorMapper recuperatorMapper;

    @Autowired
    private RecuperatorService recuperatorService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restRecuperatorMockMvc;

    private Recuperator recuperator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        final RecuperatorResource recuperatorResource = new RecuperatorResource(recuperatorService, organisationService, userService, recuperatorRepository);
        this.restRecuperatorMockMvc = MockMvcBuilders.standaloneSetup(recuperatorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Recuperator createEntity(EntityManager em) {
        Recuperator recuperator = new Recuperator()
            .name(DEFAULT_NAME)
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        // Add required entity
        Organisation organisation;
        if (TestUtil.findAll(em, Organisation.class).isEmpty()) {
            organisation = OrganisationResourceIT.createEntity(em);
            em.persist(organisation);
            em.flush();
        } else {
            organisation = TestUtil.findAll(em, Organisation.class).get(0);
        }
        return recuperator.association(organisation);
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Recuperator createUpdatedEntity(EntityManager em) {
        Recuperator recuperator = new Recuperator()
            .name(UPDATED_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        // Add required entity
        Organisation organisation;
        if (TestUtil.findAll(em, Organisation.class).isEmpty()) {
            organisation = OrganisationResourceIT.createUpdatedEntity(em);
            em.persist(organisation);
            em.flush();
        } else {
            organisation = TestUtil.findAll(em, Organisation.class).get(0);
        }
        return recuperator.association(organisation);
    }

    @BeforeEach
    void initTest() {
        recuperator = createEntity(em);
    }

    @Test
    @Transactional
    @Disabled
    public void createRecuperator() throws Exception {
        int databaseSizeBeforeCreate = recuperatorRepository.findAll().size();

        // Create the Recuperator
        RecuperatorDTO recuperatorDTO = recuperatorMapper.toDto(recuperator);
        restRecuperatorMockMvc.perform(post("/api/recuperators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperatorDTO)))
            .andExpect(status().isCreated());

        // Validate the Recuperator in the database
        List<Recuperator> recuperatorList = recuperatorRepository.findAll();
        assertThat(recuperatorList).hasSize(databaseSizeBeforeCreate + 1);
        Recuperator testRecuperator = recuperatorList.get(recuperatorList.size() - 1);
        assertThat(testRecuperator.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRecuperator.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void createRecuperatorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recuperatorRepository.findAll().size();

        // Create the Recuperator with an existing ID
        recuperator.setId(1L);
        RecuperatorDTO recuperatorDTO = recuperatorMapper.toDto(recuperator);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecuperatorMockMvc.perform(post("/api/recuperators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperatorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recuperator in the database
        List<Recuperator> recuperatorList = recuperatorRepository.findAll();
        assertThat(recuperatorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = recuperatorRepository.findAll().size();
        // set the field null
        recuperator.setName(null);

        // Create the Recuperator, which fails.
        RecuperatorDTO recuperatorDTO = recuperatorMapper.toDto(recuperator);

        restRecuperatorMockMvc.perform(post("/api/recuperators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperatorDTO)))
            .andExpect(status().isBadRequest());

        List<Recuperator> recuperatorList = recuperatorRepository.findAll();
        assertThat(recuperatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = recuperatorRepository.findAll().size();
        // set the field null
        recuperator.setPhoneNumber(null);

        // Create the Recuperator, which fails.
        RecuperatorDTO recuperatorDTO = recuperatorMapper.toDto(recuperator);

        restRecuperatorMockMvc.perform(post("/api/recuperators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperatorDTO)))
            .andExpect(status().isBadRequest());

        List<Recuperator> recuperatorList = recuperatorRepository.findAll();
        assertThat(recuperatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRecuperators() throws Exception {
        // Initialize the database
        recuperatorRepository.saveAndFlush(recuperator);

        // Get all the recuperatorList
        restRecuperatorMockMvc.perform(get("/api/recuperators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recuperator.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)));
    }

    @Test
    @Transactional
    public void getRecuperator() throws Exception {
        // Initialize the database
        recuperatorRepository.saveAndFlush(recuperator);

        // Get the recuperator
        restRecuperatorMockMvc.perform(get("/api/recuperators/{id}", recuperator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recuperator.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingRecuperator() throws Exception {
        // Get the recuperator
        restRecuperatorMockMvc.perform(get("/api/recuperators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Disabled
    public void updateRecuperator() throws Exception {
        // Initialize the database
        recuperatorRepository.saveAndFlush(recuperator);

        int databaseSizeBeforeUpdate = recuperatorRepository.findAll().size();

        // Update the recuperator
        Recuperator updatedRecuperator = recuperatorRepository.findById(recuperator.getId()).get();
        // Disconnect from session so that the updates on updatedRecuperator are not directly saved in db
        em.detach(updatedRecuperator);
        updatedRecuperator
            .name(UPDATED_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        RecuperatorDTO recuperatorDTO = recuperatorMapper.toDto(updatedRecuperator);

        restRecuperatorMockMvc.perform(put("/api/recuperators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperatorDTO)))
            .andExpect(status().isOk());

        // Validate the Recuperator in the database
        List<Recuperator> recuperatorList = recuperatorRepository.findAll();
        assertThat(recuperatorList).hasSize(databaseSizeBeforeUpdate);
        Recuperator testRecuperator = recuperatorList.get(recuperatorList.size() - 1);
        assertThat(testRecuperator.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRecuperator.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingRecuperator() throws Exception {
        int databaseSizeBeforeUpdate = recuperatorRepository.findAll().size();

        // Create the Recuperator
        RecuperatorDTO recuperatorDTO = recuperatorMapper.toDto(recuperator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecuperatorMockMvc.perform(put("/api/recuperators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recuperatorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recuperator in the database
        List<Recuperator> recuperatorList = recuperatorRepository.findAll();
        assertThat(recuperatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecuperator() throws Exception {
        // Initialize the database
        recuperatorRepository.saveAndFlush(recuperator);

        int databaseSizeBeforeDelete = recuperatorRepository.findAll().size();

        // Delete the recuperator
        restRecuperatorMockMvc.perform(delete("/api/recuperators/{id}", recuperator.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Recuperator> recuperatorList = recuperatorRepository.findAll();
        assertThat(recuperatorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recuperator.class);
        Recuperator recuperator1 = new Recuperator();
        recuperator1.setId(1L);
        Recuperator recuperator2 = new Recuperator();
        recuperator2.setId(recuperator1.getId());
        assertThat(recuperator1).isEqualTo(recuperator2);
        recuperator2.setId(2L);
        assertThat(recuperator1).isNotEqualTo(recuperator2);
        recuperator1.setId(null);
        assertThat(recuperator1).isNotEqualTo(recuperator2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecuperatorDTO.class);
        RecuperatorDTO recuperatorDTO1 = new RecuperatorDTO();
        recuperatorDTO1.setId(1L);
        RecuperatorDTO recuperatorDTO2 = new RecuperatorDTO();
        assertThat(recuperatorDTO1).isNotEqualTo(recuperatorDTO2);
        recuperatorDTO2.setId(recuperatorDTO1.getId());
        assertThat(recuperatorDTO1).isEqualTo(recuperatorDTO2);
        recuperatorDTO2.setId(2L);
        assertThat(recuperatorDTO1).isNotEqualTo(recuperatorDTO2);
        recuperatorDTO1.setId(null);
        assertThat(recuperatorDTO1).isNotEqualTo(recuperatorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(recuperatorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(recuperatorMapper.fromId(null)).isNull();
    }
}
