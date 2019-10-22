package com.coffee.gifu.service;

import com.coffee.gifu.domain.*;
import com.coffee.gifu.repository.OfferRepository;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.dto.OrganisationDTO;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.impl.OfferServiceImpl;
import com.coffee.gifu.service.mapper.OfferMapper;
import com.coffee.gifu.service.mapper.RecuperatorMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private OfferMapper offerMapper;

    @Mock
    private RecuperatorMapper recuperatorMapper;

    private OfferService offerService;

    private Offer offer;

    private OfferDTO offerDTO;

    @Before
    public void setup(){
        offerService = new OfferServiceImpl(offerRepository,offerMapper, recuperatorMapper);

        ZonedDateTime begin = ZonedDateTime.now();
        ZonedDateTime end = ZonedDateTime.now();

        offer = new Offer();
        offer.setDescription("Coucou");
        offer.setTitle("Hello");
        offer.setIsCold(true);
        offer.setAvailabilityBegin(begin);
        offer.setAvailabilityEnd(end);

        Location location = new Location();
        location.setCity("fsdfgsdfg");
        location.setId(1325434L);
        location.setPostalCode("453647897");
        location.setStreetAddress("qrsytuyuklh");
        offer.setLocation(location);

        Set<Recuperator> recuperators = new HashSet<>();
        Recuperator recuperator = new Recuperator();
        recuperator.setId(1324526L);
        recuperator.setName("qergsdfg");
        recuperator.setPhoneNumber("145426357485");
        recuperators.add(recuperator);
        offer.setRecuperators(recuperators);

        Organisation organisation = new Organisation();
        organisation.setId(12345L);
        organisation.setIdentificationCode("0123456789");
        organisation.setLocation(location);
        organisation.setLogo("test");
        organisation.setName("Test");
        organisation.setDescription("Test");
        organisation.setContactMail("Test");
        organisation.setType("ENTERPRISE");
        offer.setOrganisation(organisation);

        offerDTO = new OfferDTO();
        offerDTO.setDescription("Coucou");
        offerDTO.setTitle("Hello");
        offerDTO.setIsCold(true);
        offerDTO.setAvailabilityBegin(begin);
        offerDTO.setAvailabilityEnd(end);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCity("fsdfgsdfg");
        locationDTO.setId(1325434L);
        locationDTO.setPostalCode("453647897");
        locationDTO.setStreetAddress("qrsytuyuklh");
        offerDTO.setLocationDTO(locationDTO);

        Set<RecuperatorDTO> recuperatorDTOs = new HashSet<>();
        RecuperatorDTO recuperatorDTO = new RecuperatorDTO();
        recuperatorDTO.setId(1324526L);
        recuperatorDTO.setName("qergsdfg");
        recuperatorDTO.setPhoneNumber("145426357485");
        recuperatorDTOs.add(recuperatorDTO);
        offerDTO.setRecuperatorDTOs(recuperatorDTOs);

        OrganisationDTO enterprise = new OrganisationDTO();
        enterprise.setId(12345L);
        enterprise.setIdentificationCode("0123456789");
        enterprise.setLocationDTO(locationDTO);
        enterprise.setLogo("test");
        enterprise.setName("Test");
        enterprise.setDescription("Test");
        enterprise.setContactMail("Test");
        enterprise.setType(OrganisationType.ENTERPRISE);
        offerDTO.setEnterprise(enterprise);
    }

    @Test
    public void should_save_and_return_saved_object() throws Exception {
        //Given
        when(offerMapper.toEntity(offerDTO)).thenReturn(offer);
        when(offerRepository.save(any(Offer.class))).thenReturn(offer);
        when(offerMapper.toDto(offer)).thenReturn(offerDTO);

        //When
        OfferDTO actual = offerService.save(offerDTO);

        //Then
        verify(offerRepository, times(1)).save(offer);
        assertThat(actual).isNotNull();
        assertThat(actual.getAvailabilityEnd()).isEqualTo(offer.getAvailabilityEnd());
    }

    @Test
    public void should_find_all_object() {
        //Given
        List<Offer> offers = new ArrayList<>();
        offers.add(offer);
        List<OfferDTO> offerDTOS = new ArrayList<>();
        offerDTOS.add(offerDTO);
        when(offerRepository.findAllWithEagerRelationships()).thenReturn(offers);
        when(offerMapper.toDto(offers)).thenReturn(offerDTOS);

        //When
        List<OfferDTO> actual = offerService.findAll();

        //Then
        verify(offerRepository, times(1)).findAllWithEagerRelationships();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    public void should_find_one_object() {
        //Given
        Optional<Offer> expected = Optional.ofNullable(offer);
        when(offerRepository.findOneWithEagerRelationships(1L)).thenReturn(expected);
        when(offerMapper.toDto(offer)).thenReturn(offerDTO);

        //When
        Optional<OfferDTO> actual = offerService.findOne(1L);

        //Then
        verify(offerRepository, times(1)).findOneWithEagerRelationships(1L);
        assertThat(actual).isNotNull();
        assertThat(actual.isPresent()).isTrue();
    }

    @Test
    public void should_delete_one_object() {
        //Given
        doNothing().when(offerRepository).deleteById(1L);

        //When
        offerService.delete(1L);

        //Then
        verify(offerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void should_search_available_offer_when_any_offers_is_cold() {
        //Given
        List<Offer> offers = new ArrayList<>();
        offers.add(offer);
        List<OfferDTO> offerDTOS = new ArrayList<>();
        offerDTOS.add(offerDTO);
        when(offerRepository.searchAvailableOfferNotCold()).thenReturn(offers);
        when(offerMapper.toDto(offers)).thenReturn(offerDTOS);
        boolean isColdFilter = false;

        //When
        List<OfferDTO> actual = offerService.searchAvailableOffer(isColdFilter);

        //Then
        verify(offerRepository, times(1)).searchAvailableOffer();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    public void should_search_chosen_offer() {
        //Given
        List<Offer> offers = new ArrayList<>();
        offers.add(offer);
        List<OfferDTO> offerDTOS = new ArrayList<>();
        offerDTOS.add(offerDTO);
        when(offerRepository.searchChosenOffer()).thenReturn(offers);
        when(offerMapper.toDto(offers)).thenReturn(offerDTOS);

        //When
        List<OfferDTO> actual = offerService.searchChosenOffer();

        //Then
        verify(offerRepository, times(1)).searchChosenOffer();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    public void should_search_created_offer() {
        //Given
        List<Offer> offers = new ArrayList<>();
        List<OfferDTO> offerDTOS = new ArrayList<>();
        offerDTO.setRecuperatorDTOs(null);
        offerDTOS.add(offerDTO);
        when(offerRepository.searchCreatedOffer()).thenReturn(offers);
        when(offerMapper.toDto(offers)).thenReturn(offerDTOS);

        //When
        List<OfferDTO> actual = offerService.searchCreatedOffer();

        //Then
        verify(offerRepository, times(1)).searchCreatedOffer();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(1);
    }
}
