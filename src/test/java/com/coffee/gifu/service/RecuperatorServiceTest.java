package com.coffee.gifu.service;

import com.coffee.gifu.domain.Location;
import com.coffee.gifu.domain.Organisation;
import com.coffee.gifu.domain.OrganisationType;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.repository.RecuperatorRepository;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.OrganisationDTO;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecuperatorServiceTest {

    @Mock
    private RecuperatorRepository recuperatorRepository;

    @Mock
    private RecuperatorMapper recuperatorMapper;

    private RecuperatorService recuperatorService;

    @Before
    public void setup() {
        recuperatorService = new RecuperatorServiceImpl(recuperatorRepository, recuperatorMapper);
    }

    @Test
    public void should_Save_then_return_saved_object() throws Exception {
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
    }

    @Test
    public void findAll_should_call_the_repo_and_return_the_correctly_result() {
        // Given
        when(recuperatorRepository.findAll()).thenReturn(Arrays.asList(buildRecuperator(),buildRecuperator()));

        // When
        List<RecuperatorDTO> actualrecuperatorDTOS = recuperatorService.findAll();

        // Then
        verify(recuperatorRepository, times(1)).findAll();
        verify(recuperatorMapper, times(2)).toDto(any(Recuperator.class));

        assertThat(actualrecuperatorDTOS.size()).isNotNull();
    }

    @Test
    public void findOne_should_call_the_repo_and_return_the_correctly_result() {
        // Given
        Optional<Recuperator> expected = Optional.of(buildRecuperator());
        when(recuperatorRepository.findById(1L)).thenReturn(expected);
        when(recuperatorMapper.toDto(any(Recuperator.class))).thenReturn(buildRecuperatorDto());

        // When
        java.util.Optional<RecuperatorDTO> actual = recuperatorService.findOne(1L);

        // Then
        verify(recuperatorRepository, times(1)).findById(1L);

        assertThat(actual).isNotNull();
        assertThat(actual.get().getName()).isEqualTo(expected.get().getName());
        assertThat(actual.get().getId()).isEqualTo(expected.get().getId());
        assertThat(actual.get().getPhoneNumber()).isEqualTo(expected.get().getPhoneNumber());
    }

    @Test
    public void delete_should_return_null_if_it_called() {
        // When
        doNothing().when(recuperatorRepository).deleteById(1L);

        recuperatorService.delete(1L);

        verify(recuperatorRepository, times(1)).deleteById(1L);
        // Then
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

        Organisation organisation = new Organisation();
        organisation.setId(12345L);
        organisation.setIdentificationCode("0123456789");
        organisation.setLocation(location);
        organisation.setLogo("test");
        organisation.setName("Test");
        organisation.setDescription("Test");
        organisation.setContactMail("Test");
        organisation.setType("ASSOCIATION");
        recuperator.setAssociation(organisation);
        return recuperator;
    }

    private RecuperatorDTO buildRecuperatorDto() {
        RecuperatorDTO recuperatorDTO = new RecuperatorDTO();
        recuperatorDTO.setName("Toto");
        recuperatorDTO.setId(134526L);
        recuperatorDTO.setPhoneNumber("13456475");

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setPostalCode("675679");
        locationDTO.setStreetAddress("AREGDGJFJDSDGNG");
        locationDTO.setCity("arhsgjdhgkfjlgkh");
        locationDTO.setId(1345L);

        OrganisationDTO association = new OrganisationDTO();
        association.setId(12345L);
        association.setIdentificationCode("0123456789");
        association.setLocationDTO(locationDTO);
        association.setLogo("test");
        association.setName("Test");
        association.setDescription("Test");
        association.setContactMail("Test");
        association.setType(OrganisationType.ASSOCIATION);
        recuperatorDTO.setAssociation(association);

        return recuperatorDTO;
    }
}
