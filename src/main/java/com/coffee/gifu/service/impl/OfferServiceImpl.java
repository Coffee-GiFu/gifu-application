package com.coffee.gifu.service.impl;

import com.coffee.gifu.domain.Offer;
import com.coffee.gifu.domain.OrganisationType;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.repository.OfferRepository;
import com.coffee.gifu.service.OfferService;
import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.exception.ManagementRulesException;
import com.coffee.gifu.service.mapper.OfferMapper;
import com.coffee.gifu.service.mapper.RecuperatorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Offer}.
 */
@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private final Logger log = LoggerFactory.getLogger(OfferServiceImpl.class);

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;

    private final RecuperatorMapper recuperatorMapper;

    public OfferServiceImpl(OfferRepository offerRepository, OfferMapper offerMapper, com.coffee.gifu.service.mapper.RecuperatorMapper recuperatorMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.recuperatorMapper = recuperatorMapper;
    }

    /**
     * Save a offer.
     *
     * @param offerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OfferDTO save(OfferDTO offerDTO) {
        if (offerDTO.getEnterprise().getType() != OrganisationType.ENTERPRISE) {
            throw new ManagementRulesException("An offer need an Enterprise Type to be created/updated !");
        }
        log.debug("Request to save Offer : {}", offerDTO);
        Offer offer = offerMapper.toEntity(offerDTO);
        offer = offerRepository.save(offer);
        return offerMapper.toDto(offer);
    }

    @Override
    public OfferDTO addRecuperator(Long offerId, RecuperatorDTO recuperatorDTO) {
        Optional<Offer> offerFromDB = offerRepository.findById(offerId);
        if (offerFromDB.isEmpty()) {
            throw new EntityNotFoundException("Offer with id " + offerId);
        }
        Offer offer = offerFromDB.get();
        Recuperator recuperator = recuperatorMapper.toEntity(recuperatorDTO);
        offer.getRecuperators().add(recuperator);
        offer = offerRepository.save(offer);
        return offerMapper.toDto(offer);
    }

    @Override
    public OfferDTO validateRecuperator(Long offerId, Long selectedRecuperator) {
        Optional<Offer> offerFromDB = offerRepository.findById(offerId);
        if (offerFromDB.isEmpty()) {
            throw new EntityNotFoundException("Offer with id " + offerId);
        }
        Offer offer = offerFromDB.get();
        offer.setSelectedRecuperator(selectedRecuperator);
        offer = offerRepository.save(offer);
        return offerMapper.toDto(offer);
    }

    /**
     * Get all the offers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfferDTO> findAll() {
        log.debug("Request to get all Offers");
        return offerRepository.findAllWithEagerRelationships().stream()
            .map(offerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one offer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OfferDTO> findOne(Long id) {
        log.debug("Request to get Offer : {}", id);
        return offerRepository.findOneWithEagerRelationships(id)
            .map(offerMapper::toDto);
    }

    /**
     * Delete the offer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Offer : {}", id);
        offerRepository.deleteById(id);
    }

    /**
     * Get the offer selected.
     *
     * @return an entity list.
     */
    @Transactional(readOnly = true)
    public List<OfferDTO> searchChosenOffer() {
        log.debug("Request to get Offer by account");
        return offerRepository.searchChosenOffer().stream()
            .map(offerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get the offer create.
     *
     * @return an entity list.
     */
    @Transactional(readOnly = true)
    public List<OfferDTO> searchCreatedOffer() {
        log.debug("Request to get Offer by account");
        return offerRepository.searchCreatedOffer().stream()
            .map(offerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the available offers.
     *
     * @return the list of available entities.
     */
    @Transactional(readOnly = true)
    public List<OfferDTO> searchAvailableOffer(boolean isColdFilter) {
        log.debug("Request to get all available Offers");
        List<Offer>  listOffer;
        if (isColdFilter) {
            listOffer = offerRepository.searchAvailableOfferNotCold();
        }else{
            listOffer = offerRepository.searchAvailableOffer();
        }
        return listOffer.stream()
            .map(offerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
