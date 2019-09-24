package com.coffee.gifu.service;

import com.coffee.gifu.domain.Location;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.repository.RecuperatorRepository;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.impl.RecuperatorServiceImpl;
import com.coffee.gifu.service.mapper.RecuperatorMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecuperatorServiceTest {

    @Mock
    RecuperatorRepository recuperatorRepository;

    @Mock
    private RecuperatorMapper recuperatorMapper;

    private RecuperatorService recuperatorService;

    @Before
    public void setup() {
        recuperatorService = new RecuperatorServiceImpl(recuperatorRepository, recuperatorMapper);
    }

    @Test
    public void should_Save_then_return_saved_object() {
        //Given
        RecuperatorDTO recuperatorDTO = buildRecuperatorDto();

        Recuperator recuperator = buildRecuperator();

        when(recuperatorRepository.save(any(Recuperator.class))).thenReturn(recuperator);
        when(recuperatorMapper.toEntity(any(RecuperatorDTO.class))).thenReturn(recuperator);
        when(recuperatorMapper.toDto(any(Recuperator.class))).thenReturn(recuperatorDTO);

        //When
        RecuperatorDTO actualResult = recuperatorService.save(recuperatorDTO);

        //Then
        verify(recuperatorRepository, times(1)).save(any(Recuperator.class));
        verify(recuperatorMapper, times(1)).toEntity(any(RecuperatorDTO.class));
        verify(recuperatorMapper, times(1)).toDto(any(Recuperator.class));

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getName()).isEqualTo(recuperatorDTO.getName());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(recuperatorDTO.getPhoneNumber());
        assertThat(actualResult.getLocation()).isNotNull();
        assertThat(actualResult.getLocation().getCity()).isEqualTo(recuperatorDTO.getLocation().getCity());
        assertThat(actualResult.getLocation().getPostalCode()).isEqualTo(recuperatorDTO.getLocation().getPostalCode());
        assertThat(actualResult.getLocation().getStreetAddress()).isEqualTo(recuperatorDTO.getLocation().getStreetAddress());
    }

    @Test
    public void findAll_should_call_the_repo_and_return_the_correctly_result() {
        // Given
        when(recuperatorRepository.findAll()).thenReturn(Arrays.asList(buildRecuperator(),buildRecuperator()));

        // When
        recuperatorService.findAll();

        // Then
        verify(recuperatorRepository, times(1)).findAll();
        verify(recuperatorMapper, times(2)).toDto(any(Recuperator.class));

    }

    private Recuperator buildRecuperator() {
        Recuperator recuperator = new Recuperator();
        Location location = new Location();
        recuperator.setId(134526L);
        recuperator.setName("Toto");
        recuperator.setPhoneNumber("13456475");
        location.setId(1345L);
        location.setPostalCode("675679");
        location.setStreetAddress("AREGDGJFJDSDGNG");
        location.setCity("arhsgjdhgkfjlgkh");
        recuperator.setLocation(location);
        return recuperator;
    }

    private RecuperatorDTO buildRecuperatorDto() {
        RecuperatorDTO recuperatorDTO = new RecuperatorDTO();
        recuperatorDTO.setName("Toot");
        recuperatorDTO.setPhoneNumber("1432625233");
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setPostalCode("675679");
        locationDTO.setStreetAddress("AREGDGJFJDSDGNG");
        locationDTO.setCity("arhsgjdhgkfjlgkh");
        locationDTO.setId(132452L);
        recuperatorDTO.setLocation(locationDTO);
        return recuperatorDTO;
    }
}
