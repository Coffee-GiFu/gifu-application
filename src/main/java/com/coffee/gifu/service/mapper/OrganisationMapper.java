package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.Organisation;
import com.coffee.gifu.service.dto.OrganisationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Organisation} and its DTO {@link OrganisationDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface OrganisationMapper extends EntityMapper<OrganisationDTO, Organisation> {

    @Mapping(source = "location", target = "locationDTO")
    @Mapping(source = "type", target = "type")
    OrganisationDTO toDto(Organisation organisation);

    @Mapping(source = "locationDTO", target = "location")
    @Mapping(source = "type", target = "type")
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
