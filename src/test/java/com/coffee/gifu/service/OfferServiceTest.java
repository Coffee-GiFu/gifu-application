package com.coffee.gifu.service;

import com.coffee.gifu.domain.Location;
import com.coffee.gifu.domain.Offer;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.repository.OfferRepository;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.impl.OfferServiceImpl;
import com.coffee.gifu.service.mapper.OfferMapper;
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
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private OfferMapper offerMapper;

    private OfferService offerService;

    private Offer offer;

    private OfferDTO offerDTO;

    @Before
    public void setup(){
        offerService = new OfferServiceImpl(offerRepository,offerMapper);

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

        Recuperator recuperator = new Recuperator();
        recuperator.setId(1324526L);
        recuperator.setName("qergsdfg");
        recuperator.setPhoneNumber("145426357485");
        recuperator.setLocation(location);
        offer.setRecuperator(recuperator);

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

        RecuperatorDTO recuperatorDTO = new RecuperatorDTO();
        recuperatorDTO.setId(1324526L);
        recuperatorDTO.setName("qergsdfg");
        recuperatorDTO.setPhoneNumber("145426357485");
        recuperatorDTO.setLocationDTO(locationDTO);
        offerDTO.setRecuperatorDTO(recuperatorDTO);
    }

    @Test
    public void should_save_and_return_saved_object() {
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

        //TODO : Redo this test with toDTO(List) not toDTO(Entity)
        //Given
        List<Offer> offers = new ArrayList<>();
        offers.add(offer);
        when(offerRepository.findAll()).thenReturn(offers);
        when(offerMapper.toDto(offer)).thenReturn(offerDTO);

        //When
        List<OfferDTO> actual = offerService.findAll();

        //Then
        verify(offerRepository, times(1)).findAll();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    public void should_find_one_object() {
        //Given
        Optional<Offer> expected = Optional.ofNullable(offer);
        when(offerRepository.findById(1L)).thenReturn(expected);
        when(offerMapper.toDto(offer)).thenReturn(offerDTO);

        //When
        Optional<OfferDTO> actual = offerService.findOne(1L);

        //Then
        verify(offerRepository, times(1)).findById(1L);
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

}
