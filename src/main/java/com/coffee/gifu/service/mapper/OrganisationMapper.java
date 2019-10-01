package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.*;
import com.coffee.gifu.service.dto.OrganisationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organisation} and its DTO {@link OrganisationDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface OrganisationMapper extends EntityMapper<OrganisationDTO, Organisation> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.city", target = "locationCity")
    OrganisationDTO toDto(Organisation organisation);

    @Mapping(source = "locationId", target = "location")
    Organisation toEntity(OrganisationDTO organisationDTO);

    default Organisation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organisation organisation = new Organisation();
        organisation.setId(id);
        return organisation;
    }
}