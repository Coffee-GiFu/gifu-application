package com.coffee.gifu.service.impl;

import com.coffee.gifu.domain.OrganisationType;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.repository.RecuperatorRepository;
import com.coffee.gifu.service.RecuperatorService;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.mapper.RecuperatorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Recuperator}.
 */
@Service
@Transactional
public class RecuperatorServiceImpl implements RecuperatorService {

    private final Logger log = LoggerFactory.getLogger(RecuperatorServiceImpl.class);

    private final RecuperatorRepository recuperatorRepository;

    private final RecuperatorMapper recuperatorMapper;

    public RecuperatorServiceImpl(RecuperatorRepository recuperatorRepository, RecuperatorMapper recuperatorMapper) {
        this.recuperatorRepository = recuperatorRepository;
        this.recuperatorMapper = recuperatorMapper;
    }

    /**
     * Save a recuperator.
     *
     * @param recuperatorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RecuperatorDTO save(RecuperatorDTO recuperatorDTO) throws Exception {
        if (recuperatorDTO.getAssociation().getType() != OrganisationType.ASSOCIATION) {
            throw new Exception("Big Problema");
        }
        log.debug("Request to save Recuperator : {}", recuperatorDTO);
        Recuperator recuperator = recuperatorMapper.toEntity(recuperatorDTO);
        recuperator = recuperatorRepository.save(recuperator);
        return recuperatorMapper.toDto(recuperator);
    }

    /**
     * Get all the recuperators.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RecuperatorDTO> findAll() {
        log.debug("Request to get all Recuperators");
        return recuperatorRepository.findAll().stream()
            .map(recuperatorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one recuperator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RecuperatorDTO> findOne(Long id) {
        log.debug("Request to get Recuperator : {}", id);
        return recuperatorRepository.findById(id)
            .map(recuperatorMapper::toDto);
    }

    /**
     * Delete the recuperator by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recuperator : {}", id);
        recuperatorRepository.deleteById(id);
    }
}
