package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.*;
import com.coffee.gifu.service.dto.RecuperatorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Recuperator} and its DTO {@link RecuperatorDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface RecuperatorMapper extends EntityMapper<RecuperatorDTO, Recuperator> {

    @Mapping(source = "location", target = "locationDTO")
    RecuperatorDTO toDto(Recuperator offer);

    @Mapping(source = "locationDTO", target = "location")
    Recuperator toEntity(RecuperatorDTO offerDTO);

    default Recuperator fromId(Long id) {
        if (id == null || id == -5) {
            return null;
        }
        Recuperator recuperator = new Recuperator();
        recuperator.setId(id);
        return recuperator;
    }
}
