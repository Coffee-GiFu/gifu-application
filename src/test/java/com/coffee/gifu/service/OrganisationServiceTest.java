package com.coffee.gifu.service;

import com.coffee.gifu.domain.Location;
import com.coffee.gifu.domain.Offer;
import com.coffee.gifu.domain.Organisation;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.repository.OfferRepository;
import com.coffee.gifu.repository.OrganisationRepository;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.dto.OrganisationDTO;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.impl.OfferServiceImpl;
import com.coffee.gifu.service.impl.OrganisationServiceImpl;
import com.coffee.gifu.service.mapper.OfferMapper;
import com.coffee.gifu.service.mapper.OrganisationMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrganisationServiceTest {

    @Mock
    private OrganisationRepository organisationRepository;

    @Mock
    private OrganisationMapper organisationMapper;

    private OrganisationService organisationService;

    private Organisation organisation;

    private OrganisationDTO organisationDTO;

    @Before
    public void setup() {
        organisationService = new OrganisationServiceImpl(organisationRepository, organisationMapper);

        organisation = new Organisation();
        organisation.setDescription("Test");
        organisation.setIdentificationCode("0123456789");
        organisation.setContactMail("test@test.fr");
        organisation.setLogo("path");
        organisation.setName("name");
        organisation.setPhoneNumber("0123456789");
        organisation.setId(1L);

        Location location = new Location();
        location.setCity("city");
        location.setId(1325434L);
        location.setPostalCode("453647897");
        location.setStreetAddress("1 rue du test");
        organisation.setLocation(location);

        organisationDTO = new OrganisationDTO();
        organisationDTO.setDescription("Test");
        organisationDTO.setIdentificationCode("0123456789");
        organisationDTO.setContactMail("test@test.fr");
        organisationDTO.setLogo("path");
        organisationDTO.setName("name");
        organisationDTO.setPhoneNumber("0123456789");
        organisationDTO.setId(1L);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCity("city");
        locationDTO.setId(1325434L);
        locationDTO.setPostalCode("453647897");
        locationDTO.setStreetAddress("1 rue du Test");
        organisationDTO.setLocationDTO(locationDTO);
    }

    @Test
    public void should_save_and_return_saved_object() {
        //Given
        when(organisationMapper.toEntity(organisationDTO)).thenReturn(organisation);
        when(organisationRepository.save(any(Organisation.class))).thenReturn(organisation);
        when(organisationMapper.toDto(organisation)).thenReturn(organisationDTO);

        //When
        OrganisationDTO actual = organisationService.save(organisationDTO);

        //Then
        verify(organisationRepository, times(1)).save(organisation);
        assertThat(actual).isNotNull();
        assertThat(actual.getIdentificationCode()).isEqualTo(organisation.getIdentificationCode());
    }

    @Test
    public void should_find_all_object() {
        //Given
        Optional<Organisation> expected = Optional.ofNullable(organisation);
        when(organisationRepository.findById(1L)).thenReturn(expected);
        when(organisationMapper.toDto(organisation)).thenReturn(organisationDTO);

        //When
        Optional<OrganisationDTO> actual = organisationService.findOne(1L);

        //Then
        verify(organisationRepository, times(1)).findById(1L);
        assertThat(actual).isNotNull();
        assertThat(actual.isPresent()).isTrue();
    }
}
