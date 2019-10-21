package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.Location;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecuperatorMapperTest {

    @Autowired
    private RecuperatorMapper recuperatorMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testEntityToDTO() {
        //Given
        Recuperator recuperator = new Recuperator();
        Location location = new Location();
        recuperator.setId(134526L);
        recuperator.setName("Toto");
        recuperator.setPhoneNumber("13456475");

        //When
        RecuperatorDTO recuperatorDTO = recuperatorMapper.toDto(recuperator);

        //Then
        assertThat(recuperatorDTO).isNotNull();
        assertThat(recuperatorDTO.getPhoneNumber()).isEqualTo(recuperator.getPhoneNumber());
        assertThat(recuperatorDTO.getName()).isEqualTo(recuperator.getName());
        assertThat(recuperatorDTO.getId()).isEqualTo(recuperator.getId());
    }

    @Test
    public void testDTOToEntity() {
        //Given
        RecuperatorDTO recuperatorDTO = new RecuperatorDTO();
        recuperatorDTO.setName("Toto");
        recuperatorDTO.setPhoneNumber("13456475");

        //When
        Recuperator recuperator = recuperatorMapper.toEntity(recuperatorDTO);

        //Then
        assertThat(recuperator).isNotNull();
        assertThat(recuperator.getPhoneNumber()).isEqualTo(recuperatorDTO.getPhoneNumber());
        assertThat(recuperator.getName()).isEqualTo(recuperatorDTO.getName());
    }

    @Test
    public void when_id_equal_null_return_null(){
        // Given
        Long id = null;

        // When
        Recuperator actual = recuperatorMapper.fromId(id);

        // Then
        assertThat(actual).isNull();
    }

    @Test
    public void when_id_equal_1_return_recuperator(){
        // Given
        Long id = 1L;

        // When
        Recuperator actual = recuperatorMapper.fromId(id);

        // Then
        assertThat(actual).isNotNull();
    }
}
