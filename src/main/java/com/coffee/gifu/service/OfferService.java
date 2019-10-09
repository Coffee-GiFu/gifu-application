package com.coffee.gifu.service;

import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.exception.ManagementRulesException;

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
    OfferDTO save(OfferDTO offerDTO) throws ManagementRulesException;

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
}
