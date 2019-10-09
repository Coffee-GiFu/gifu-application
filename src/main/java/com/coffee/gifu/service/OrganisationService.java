package com.coffee.gifu.service;

import com.coffee.gifu.service.dto.OrganisationDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.coffee.gifu.domain.Organisation}.
 */
public interface OrganisationService {

    /**
     * Save a organisation.
     *
     * @param organisationDTO the entity to save.
     * @return the persisted entity.
     */
    OrganisationDTO save(OrganisationDTO organisationDTO);

    /**
     * Get the "id" organisation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrganisationDTO> findOne(Long id);
}
