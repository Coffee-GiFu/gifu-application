package com.coffee.gifu.web.rest;

import com.coffee.gifu.domain.User;
import com.coffee.gifu.security.AuthoritiesConstants;
import com.coffee.gifu.security.SecurityUtils;
import com.coffee.gifu.service.OfferService;
import com.coffee.gifu.service.OrganisationService;
import com.coffee.gifu.service.UserService;
import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.dto.OrganisationDTO;
import com.coffee.gifu.service.exception.ManagementRulesException;
import com.coffee.gifu.web.rest.errors.BadRequestAlertException;
import com.coffee.gifu.web.rest.errors.CurrentUserLoginNotFound;
import com.coffee.gifu.web.rest.errors.EnterpriseNotFoundException;
import com.coffee.gifu.web.rest.errors.WrongOrganisationTypeException;
import com.coffee.gifu.web.rest.request.object.CreateOfferRequest;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.coffee.gifu.domain.Offer}.
 */
@RestController
@RequestMapping("/api")
public class OfferResource {

    private final Logger log = LoggerFactory.getLogger(OfferResource.class);

    private static final String ENTITY_NAME = "offer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferService offerService;
    private final UserService userService;
    private final OrganisationService organisationService;

    public OfferResource(OfferService offerService, OrganisationService organisationService,
                         UserService userService) {
        this.offerService = offerService;
        this.organisationService = organisationService;
        this.userService = userService;
    }

    /**
     * {@code POST  /offers} : Create a new offer.
     *
     * @param createOfferRequest The request to create an offer.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerDTO, or with status {@code 400 (Bad Request)} if the offer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offers")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.COMPANY + "\")")
    public ResponseEntity<OfferDTO> createOffer(@Valid @RequestBody CreateOfferRequest createOfferRequest) throws URISyntaxException {
        log.debug("REST request to save Offer : {}", createOfferRequest);
        log.debug("REST request to save Offer : getCold {}", createOfferRequest.getIsCold());
        Optional<User> userWithAuthoritiesByLogin = checkIfUserExists();

        Optional<OrganisationDTO> organisation = checkIfOrganisationExists(userWithAuthoritiesByLogin);

        OfferDTO savedOfferToReturn = createOfferDTO(createOfferRequest, organisation);

        return ResponseEntity.created(new URI("/api/offers/" + savedOfferToReturn.getId()))
                .headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, true,
                                ENTITY_NAME, savedOfferToReturn.getId().toString()))
                .body(savedOfferToReturn);
    }

    private Optional<OrganisationDTO> checkIfOrganisationExists
            (@RequestBody @Valid Optional<User> userWithAuthoritiesByLogin) {
        if (organisationService.findOne(userWithAuthoritiesByLogin.get().getOrganisationID()).isEmpty()) {
            throw new CurrentUserLoginNotFound("Organisation not found for this id " + userWithAuthoritiesByLogin.get().getId());
        }
        Optional<OrganisationDTO> optionalOrganisationDTO = organisationService.findOne(userWithAuthoritiesByLogin.get().getOrganisationID());
        if (optionalOrganisationDTO.isEmpty()) {
            throw new EnterpriseNotFoundException("Organisation not found for this id " + optionalOrganisationDTO.get().getId());
        }
        return optionalOrganisationDTO;
    }

    private OfferDTO createOfferDTO(@RequestBody @Valid CreateOfferRequest
                                            createOfferRequest, Optional<OrganisationDTO> optionalOrganisationDTO) {
        OfferDTO offerDTO = createOfferDTOFromRequest(createOfferRequest);
        if (optionalOrganisationDTO.isEmpty()) {
            throw new WrongOrganisationTypeException();
        }
        OrganisationDTO organisationDTO = optionalOrganisationDTO.get();
        offerDTO.setEnterprise(organisationDTO);
        return offerService.save(offerDTO);
    }

    private Optional<User> checkIfUserExists() {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin.isEmpty()) {
            throw new CurrentUserLoginNotFound("currentUserLogin is not found");
        }

        Optional<User> userWithAuthoritiesByLogin =
                userService.getUserWithAuthoritiesByLogin(currentUserLogin.get());
        if (userWithAuthoritiesByLogin.isEmpty()) {
            throw new CurrentUserLoginNotFound("userWithAuthoritiesByLogin is not found");
        }
        return userWithAuthoritiesByLogin;
    }

    private OfferDTO createOfferDTOFromRequest(@RequestBody @Valid CreateOfferRequest createOfferRequest) {
        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setDescription(createOfferRequest.getDescription());
        offerDTO.setAvailabilityBegin(createOfferRequest.getAvailabilityBegin());
        offerDTO.setAvailabilityEnd(createOfferRequest.getAvailabilityEnd());
        offerDTO.setTitle(createOfferRequest.getTitle());
        offerDTO.setIsCold(createOfferRequest.getIsCold());
        offerDTO.setLocationDTO(createOfferRequest.getLocationDTO());
        return offerDTO;
    }

    /**
     * {@code PUT  /offers} : Updates an existing offer.
     *
     * @param offerDTO the offerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerDTO,
     * or with status {@code 400 (Bad Request)} if the offerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offers")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.COMPANY + "\")")
    public ResponseEntity<OfferDTO> updateOffer(@Valid @RequestBody OfferDTO offerDTO) {
        log.debug("REST request to update Offer : {}", offerDTO);
        if (offerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferDTO result = null;
        try {
            result = offerService.save(offerDTO);
        } catch (ManagementRulesException e) {
            throw new WrongOrganisationTypeException();
        }
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /offers} : get all the offers.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offers in body.
     */
    @GetMapping("/offers")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public List<OfferDTO> getAllOffers(
            @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Offers");
        return offerService.findAll();
    }

    /**
     * {@code GET  /offers/:id} : get the "id" offer.
     *
     * @param id the id of the offerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offers/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<OfferDTO> getOffer(@PathVariable Long id) {
        log.debug("REST request to get Offer : {}", id);
        Optional<OfferDTO> offerDTO = offerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerDTO);
    }

    /**
     * {@code DELETE  /offers/:id} : delete the "id" offer.
     *
     * @param id the id of the offerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offers/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        log.debug("REST request to delete Offer : {}", id);
        offerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /offers/selected} : get the selecte offer.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerDTO,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offers/selected")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ASSOCIATION + "\")")
    public List<OfferDTO> searchChosenOffer() {
        log.debug("REST request to get Offer.");
        return offerService.searchChosenOffer();
    }

    /**
     * {@code GET  /offers/create} : get the create offer.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerDTO,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offers/create")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.COMPANY + "\")")
    public List<OfferDTO> searchCreatedOffer() {
        log.debug("REST request to get Offer.");
        return offerService.searchCreatedOffer();
    }

    /**
     * {@code POST  /offers/available : get the available offer.
     *
     * @param isColdFilter
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerDTO,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offers/available/{isColdFilter}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public List<OfferDTO> searchAvailableOffer(@PathVariable boolean isColdFilter) {
        log.debug("REST request to get available Offer : {}", isColdFilter);
        return offerService.searchAvailableOffer(isColdFilter);
    }
}
