package com.coffee.gifu.web.rest;

import com.coffee.gifu.GifuApp;
import com.coffee.gifu.domain.*;
import com.coffee.gifu.repository.AuthorityRepository;
import com.coffee.gifu.repository.OfferRepository;
import com.coffee.gifu.repository.UserRepository;
import com.coffee.gifu.security.AuthoritiesConstants;
import com.coffee.gifu.service.MailService;
import com.coffee.gifu.service.OfferService;
import com.coffee.gifu.service.OrganisationService;
import com.coffee.gifu.service.UserService;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.dto.OrganisationDTO;
import com.coffee.gifu.service.mapper.OfferMapper;
import com.coffee.gifu.web.rest.errors.ExceptionTranslator;
import com.coffee.gifu.web.rest.request.object.CreateOfferRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

import static com.coffee.gifu.web.rest.TestUtil.createFormattingConversionService;
import static com.coffee.gifu.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OfferResource} REST controller.
 */
@SpringBootTest(classes = GifuApp.class)
public class OfferResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_COLD = false;
    private static final Boolean UPDATED_IS_COLD = true;

    private static final ZonedDateTime DEFAULT_AVAILABILITY_BEGIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_AVAILABILITY_BEGIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_AVAILABILITY_BEGIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final ZonedDateTime DEFAULT_AVAILABILITY_END = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_AVAILABILITY_END = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_AVAILABILITY_END = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private OfferRepository offerRepository;

    @Mock
    private OfferRepository offerRepositoryMock;

    @Autowired
    private OfferMapper offerMapper;

    @Mock
    private OfferService offerServiceMock;

    @Autowired
    private OfferService offerService;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private UserService userService;

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

    private MockMvc restOfferMockMvc;

    private Offer offer;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpMessageConverter<?>[] httpMessageConverters;


    @Mock
    private UserService mockUserService;

    @Mock
    private MailService mockMailService;

    private MockMvc restMvc;

    private MockMvc restUserMockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferResource offerResource = new OfferResource(offerService, organisationService, userService);
        this.restOfferMockMvc = MockMvcBuilders.standaloneSetup(offerResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setConversionService(createFormattingConversionService())
                .setMessageConverters(jacksonMessageConverter)
                .setValidator(validator).build();
        MockitoAnnotations.initMocks(this);
        doNothing().when(mockMailService).sendActivationEmail(any());
        AccountResource accountResource =
                new AccountResource(userRepository, userService, mockMailService);

        AccountResource accountUserMockResource =
                new AccountResource(userRepository, mockUserService, mockMailService);
        this.restMvc = MockMvcBuilders.standaloneSetup(accountResource)
                .setMessageConverters(httpMessageConverters)
                .setControllerAdvice(exceptionTranslator)
                .build();
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(accountUserMockResource)
                .setControllerAdvice(exceptionTranslator)
                .build();
    }

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offer createEntity(EntityManager em) {
        Offer offer = new Offer()
                .description(DEFAULT_DESCRIPTION)
                .isCold(DEFAULT_IS_COLD)
                .availabilityBegin(DEFAULT_AVAILABILITY_BEGIN)
                .availabilityEnd(DEFAULT_AVAILABILITY_END)
                .title(DEFAULT_TITLE);
        // Add required entity
        Location location;
        location = LocationITResource.createEntity(em);
        offer.setLocation(location);
        // Add required entity
        Organisation organisation;
        if (TestUtil.findAll(em, Organisation.class).isEmpty()) {
            organisation = OrganisationResourceIT.createEntity(em);
            em.persist(organisation);
            em.flush();
        } else {
            organisation = TestUtil.findAll(em, Organisation.class).get(0);
        }
        offer.setOrganisation(organisation);
        return offer;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offer createUpdatedEntity(EntityManager em) {
        Offer offer = new Offer()
                .description(UPDATED_DESCRIPTION)
                .isCold(UPDATED_IS_COLD)
                .availabilityBegin(UPDATED_AVAILABILITY_BEGIN)
                .availabilityEnd(UPDATED_AVAILABILITY_END)
                .title(UPDATED_TITLE);
        // Add required entity
        Location location;
        if (TestUtil.findAll(em, Location.class).isEmpty()) {
            location = LocationITResource.createUpdatedEntity(em);
            em.persist(location);
            em.flush();
        } else {
            location = TestUtil.findAll(em, Location.class).get(0);
        }
        offer.setLocation(location);
        // Add required entity
        Organisation organisation;
        if (TestUtil.findAll(em, Organisation.class).isEmpty()) {
            organisation = OrganisationResourceIT.createUpdatedEntity(em);
            em.persist(organisation);
            em.flush();
        } else {
            organisation = TestUtil.findAll(em, Organisation.class).get(0);
        }
        offer.setOrganisation(organisation);
        return offer;
    }

    @BeforeEach
    public void initTest() {

        offer = createEntity(em);
    }

    @Disabled
    @Test
    @Transactional()
    public void createOffer() throws Exception {

        int databaseSizeBeforeCreate = offerRepository.findAll().size();
        // Login
        restUserMockMvc.perform(get("/api/authenticate")
                .with(request -> {
                    request.setRemoteUser("test");
                    return request;
                })
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("test"));

        // Create the Offer
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCity("Paris");
        locationDTO.setPostalCode("75012");
        locationDTO.setStreetAddress("12 avenue de charenton");

        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setDescription(DEFAULT_DESCRIPTION);
        offerDTO.setAvailabilityBegin(DEFAULT_AVAILABILITY_BEGIN);
        offerDTO.setAvailabilityEnd(DEFAULT_AVAILABILITY_END);
        offerDTO.setIsCold(DEFAULT_IS_COLD);
        offerDTO.setTitle(DEFAULT_TITLE);
        offerDTO.setLocationDTO(locationDTO);

        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.ADMIN);
        authorities.add(authority);

        User user = new User();
        user.setOrganisationID(1L);
        user.setLogin("test");
        user.setEmail("john.doe@jhipster.com");
        user.setLangKey("en");
        user.setAuthorities(authorities);
        when(mockUserService.getUserWithAuthorities()).thenReturn(Optional.of(user));

        Optional<OrganisationDTO> optionalOrganisationDTO = organisationService.findOne(mockUserService.getUserWithAuthorities().get().getOrganisationID());
        offerDTO.setEnterprise(optionalOrganisationDTO.get());

        restOfferMockMvc.perform(post("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isOk());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate + 1);

        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOffer.isIsCold()).isEqualTo(DEFAULT_IS_COLD);
        assertThat(testOffer.getAvailabilityBegin()).isEqualTo(DEFAULT_AVAILABILITY_BEGIN);
        assertThat(testOffer.getAvailabilityEnd()).isEqualTo(DEFAULT_AVAILABILITY_END);
        assertThat(testOffer.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testOffer.getOrganisation()).isEqualTo(offer.getOrganisation());
        assertThat(testOffer.getLocation().getStreetAddress()).isEqualTo(locationDTO.getStreetAddress());
    }

    @Test
    @Transactional()
    public void createOffer_should_return_not_found_if_idEnterprise_not_found() throws Exception {

        // Create the Offer
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCity("Paris");
        locationDTO.setPostalCode("75012");
        locationDTO.setStreetAddress("12 avenue de charenton");
        CreateOfferRequest createOfferRequest = new CreateOfferRequest();
        createOfferRequest.setAvailabilityBegin(DEFAULT_AVAILABILITY_BEGIN);
        createOfferRequest.setAvailabilityEnd(DEFAULT_AVAILABILITY_END);
        createOfferRequest.setIsCold(DEFAULT_IS_COLD);
        createOfferRequest.setDescription(DEFAULT_DESCRIPTION);
        createOfferRequest.setTitle(DEFAULT_TITLE);

        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setDescription(createOfferRequest.getDescription());
        offerDTO.setAvailabilityBegin(createOfferRequest.getAvailabilityBegin());
        offerDTO.setAvailabilityEnd(createOfferRequest.getAvailabilityEnd());
        offerDTO.setTitle(createOfferRequest.getTitle());
        offerDTO.setLocationDTO(locationDTO);
        OrganisationDTO organisationDTO = new OrganisationDTO();
        organisationDTO.setId(11111L);
        offerDTO.setEnterprise(organisationDTO);

        restOfferMockMvc.perform(post("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isNotFound());
    }

    @Disabled
    @Test
    @Transactional
    public void createOfferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerRepository.findAll().size();

        // Create the Offer with an existing ID
        offer.setId(1L);
        OfferDTO offerDTO = offerMapper.toDto(offer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferMockMvc.perform(post("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setDescription(null);

        // Create the Offer, which fails.
        OfferDTO offerDTO = offerMapper.toDto(offer);

        restOfferMockMvc.perform(post("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityBeginIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setAvailabilityBegin(null);

        // Create the Offer, which fails.
        OfferDTO offerDTO = offerMapper.toDto(offer);

        restOfferMockMvc.perform(post("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityEndIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setAvailabilityEnd(null);

        // Create the Offer, which fails.
        OfferDTO offerDTO = offerMapper.toDto(offer);

        restOfferMockMvc.perform(post("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setTitle(null);

        // Create the Offer, which fails.
        OfferDTO offerDTO = offerMapper.toDto(offer);

        restOfferMockMvc.perform(post("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOffers() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get all the offerList
        restOfferMockMvc.perform(get("/api/offers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(offer.getId().intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].isCold").value(hasItem(DEFAULT_IS_COLD.booleanValue())))
                .andExpect(jsonPath("$.[*].availabilityBegin").value(hasItem(sameInstant(DEFAULT_AVAILABILITY_BEGIN))))
                .andExpect(jsonPath("$.[*].availabilityEnd").value(hasItem(sameInstant(DEFAULT_AVAILABILITY_END))))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }

    @Test
    @Transactional
    public void getOffer() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", offer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(offer.getId().intValue()))
                .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.isCold").value(DEFAULT_IS_COLD.booleanValue()))
                .andExpect(jsonPath("$.availabilityBegin").value(sameInstant(DEFAULT_AVAILABILITY_BEGIN)))
                .andExpect(jsonPath("$.availabilityEnd").value(sameInstant(DEFAULT_AVAILABILITY_END)))
                .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOffer() throws Exception {
        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOffer() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Update the offer
        Offer updatedOffer = offerRepository.findById(offer.getId()).get();
        // Disconnect from session so that the updates on updatedOffer are not directly saved in db
        em.detach(updatedOffer);
        updatedOffer
                .description(UPDATED_DESCRIPTION)
                .isCold(UPDATED_IS_COLD)
                .availabilityBegin(UPDATED_AVAILABILITY_BEGIN)
                .availabilityEnd(UPDATED_AVAILABILITY_END)
                .title(UPDATED_TITLE);
        OfferDTO offerDTO = offerMapper.toDto(updatedOffer);

        restOfferMockMvc.perform(put("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isOk());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOffer.isIsCold()).isEqualTo(UPDATED_IS_COLD);
        assertThat(testOffer.getAvailabilityBegin()).isEqualTo(UPDATED_AVAILABILITY_BEGIN);
        assertThat(testOffer.getAvailabilityEnd()).isEqualTo(UPDATED_AVAILABILITY_END);
        assertThat(testOffer.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingOffer() throws Exception {
        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Create the Offer
        OfferDTO offerDTO = offerMapper.toDto(offer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferMockMvc.perform(put("/api/offers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(offerDTO)))
                .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOffer() throws Exception {
        // Initialize the database
        offerRepository.saveAndFlush(offer);

        int databaseSizeBeforeDelete = offerRepository.findAll().size();

        // Delete the offer
        restOfferMockMvc.perform(delete("/api/offers/{id}", offer.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Offer.class);
        Offer offer1 = new Offer();
        offer1.setId(1L);
        Offer offer2 = new Offer();
        offer2.setId(offer1.getId());
        assertThat(offer1).isEqualTo(offer2);
        offer2.setId(2L);
        assertThat(offer1).isNotEqualTo(offer2);
        offer1.setId(null);
        assertThat(offer1).isNotEqualTo(offer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferDTO.class);
        OfferDTO offerDTO1 = new OfferDTO();
        offerDTO1.setId(1L);
        OfferDTO offerDTO2 = new OfferDTO();
        assertThat(offerDTO1).isNotEqualTo(offerDTO2);
        offerDTO2.setId(offerDTO1.getId());
        assertThat(offerDTO1).isEqualTo(offerDTO2);
        offerDTO2.setId(2L);
        assertThat(offerDTO1).isNotEqualTo(offerDTO2);
        offerDTO1.setId(null);
        assertThat(offerDTO1).isNotEqualTo(offerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(offerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(offerMapper.fromId(null)).isNull();
    }
}
