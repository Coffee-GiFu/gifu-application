package com.coffee.gifu.service;

import com.coffee.gifu.service.dto.OfferDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.coffee.gifu.domain.Offer}.
 */
public interface OfferService {

    /**
     * Save a offer.
     *
     * @param offerDTO the entity to save.
     * @return the persisted entity.
     */
    OfferDTO save(OfferDTO offerDTO);

    /**
     * Get all the offers.
     *
     * @return the list of entities.
     */
    List<OfferDTO> findAll();


    /**
     * Get the "id" offer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OfferDTO> findOne(Long id);

    /**
     * Delete the "id" offer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the available offers.
     *
     * @param isColdFilter
     * @return the list of available entities.
     */
    List<OfferDTO> searchAvailableOffer(Boolean isColdFilter);

    /**
     * Get all the selected offers for actual account.
     *
     * @return the list of available entities.
     */
    List<OfferDTO> searchChosenOffer();

    /**
     * Get all the created offers for actual account.
     *
     * @return the list of available entities.
     */
    List<OfferDTO> searchCreatedOffer();
}
