package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.Offer;
import com.coffee.gifu.service.dto.OfferDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Offer} and its DTO {@link OfferDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, RecuperatorMapper.class, OrganisationMapper.class})
public interface OfferMapper extends EntityMapper<OfferDTO, Offer> {

    @Mapping(source = "location", target = "locationDTO")
    @Mapping(source = "organisation", target = "enterprise")
    @Mapping(source = "recuperators", target = "recuperatorDTOs")
    OfferDTO toDto(Offer offer);

    @Mapping(source = "locationDTO", target = "location")
    @Mapping(source = "enterprise", target = "organisation")
    @Mapping(source = "recuperatorDTOs", target = "recuperators")
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
