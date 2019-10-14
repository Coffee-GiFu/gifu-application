package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Recuperator} and its DTO {@link RecuperatorDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, OrganisationMapper.class})
public interface RecuperatorMapper extends EntityMapper<RecuperatorDTO, Recuperator> {

    @Mapping(source = "location", target = "locationDTO")
    @Mapping(source = "association", target = "association")
    RecuperatorDTO toDto(Recuperator recuperator);

    @Mapping(source = "locationDTO", target = "location")
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
