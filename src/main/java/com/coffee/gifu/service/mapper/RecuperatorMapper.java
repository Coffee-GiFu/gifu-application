package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.*;
import com.coffee.gifu.service.dto.RecuperatorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Recuperator} and its DTO {@link RecuperatorDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrganisationMapper.class})
public interface RecuperatorMapper extends EntityMapper<RecuperatorDTO, Recuperator> {

    @Mapping(source = "association", target = "association")
    RecuperatorDTO toDto(Recuperator recuperator);

    @Mapping(source = "association", target = "association")
    Recuperator toEntity(RecuperatorDTO recuperatorDTO);

    default Recuperator fromId(Long id) {
        if (id == null) {
            return null;
        }
        Recuperator recuperator = new Recuperator();
        recuperator.setId(id);
        return recuperator;
    }
}
