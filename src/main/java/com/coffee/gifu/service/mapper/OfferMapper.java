package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.*;
import com.coffee.gifu.service.dto.OfferDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Offer} and its DTO {@link OfferDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, RecuperatorMapper.class})
public interface OfferMapper extends EntityMapper<OfferDTO, Offer> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "recuperator.id", target = "recuperatorId")
    OfferDTO toDto(Offer offer);

    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "recuperatorId", target = "recuperator")
    Offer toEntity(OfferDTO offerDTO);

    default Offer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Offer offer = new Offer();
        offer.setId(id);
        return offer;
    }
}
