package com.coffee.gifu.web.rest;

import com.coffee.gifu.security.AuthoritiesConstants;
import com.coffee.gifu.service.OfferService;
import com.coffee.gifu.web.rest.errors.BadRequestAlertException;
import com.coffee.gifu.service.dto.OfferDTO;

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

    public OfferResource(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * {@code POST  /offers} : Create a new offer.
     *
     * @param offerDTO the offerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerDTO, or with status {@code 400 (Bad Request)} if the offer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offers")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.COMPANY + "\")")
    public ResponseEntity<OfferDTO> createOffer(@Valid @RequestBody OfferDTO offerDTO) throws URISyntaxException {
        log.debug("REST request to save Offer : {}", offerDTO);
        if (offerDTO.getId() != null) {
            throw new BadRequestAlertException("A new offer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferDTO result = offerService.save(offerDTO);
        return ResponseEntity.created(new URI("/api/offers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
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
    public ResponseEntity<OfferDTO> updateOffer(@Valid @RequestBody OfferDTO offerDTO) throws URISyntaxException {
        log.debug("REST request to update Offer : {}", offerDTO);
        if (offerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferDTO result = offerService.save(offerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offers} : get all the offers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offers in body.
     */
    @GetMapping("/offers")
    public List<OfferDTO> getAllOffers() {
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
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.COMPANY + "\")")
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
    public List<OfferDTO> searchAvailableOffer(@PathVariable boolean isColdFilter) {
        log.debug("REST request to get available Offer : {}", isColdFilter);
        return offerService.searchAvailableOffer(isColdFilter);
    }
}
