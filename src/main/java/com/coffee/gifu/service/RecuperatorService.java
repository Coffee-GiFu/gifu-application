package com.coffee.gifu.service;

import com.coffee.gifu.service.dto.RecuperatorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.coffee.gifu.domain.Recuperator}.
 */
public interface RecuperatorService {

    /**
     * Save a recuperator.
     *
     * @param recuperatorDTO the entity to save.
     * @return the persisted entity.
     */
    RecuperatorDTO save(RecuperatorDTO recuperatorDTO);

    /**
     * Get all the recuperators.
     *
     * @return the list of entities.
     */
    List<RecuperatorDTO> findAll();


    /**
     * Get the "id" recuperator.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RecuperatorDTO> findOne(Long id);

    /**
     * Delete the "id" recuperator.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
