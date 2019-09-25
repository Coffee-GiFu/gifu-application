package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.Location;
import com.coffee.gifu.domain.Offer;
import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.OfferDTO;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferMapperTest {

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private RecuperatorMapper recuperatorMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testEntityToDTO() {
        //Given
        Recuperator recuperator = new Recuperator();
        Location location = new Location();
        Offer offer = new Offer();
        recuperator.setId(134526L);
        recuperator.setName("Toto");
        recuperator.setPhoneNumber("13456475");
        location.setId(1345L);
        location.setPostalCode("675679");
        location.setStreetAddress("AREGDGJFJDSDGNG");
        location.setCity("arhsgjdhgkfjlgkh");
        recuperator.setLocation(location);
        location.setId(13465L);
        location.setPostalCode("34553");
        location.setStreetAddress("AREGDGJsdgsdfsgFJDSDGNG");
        location.setCity("stayzuioipoiueyzrteafgsdg");
        offer.setId(149876L);
        offer.setDescription("gergsghshsgfh");
        offer.setIsCold(true);
        offer.setAvailabilityBegin(ZonedDateTime.now());
        offer.setAvailabilityEnd(ZonedDateTime.now());
        offer.setTitle("TROREALARL");
        offer.setLocation(location);
        offer.setRecuperator(recuperator);

        //When
        OfferDTO offerDTO = offerMapper.toDto(offer);

        //Then
        assertThat(offerDTO).isNotNull();
        assertThat(offerDTO.getRecuperatorDTO()).isEqualTo(recuperatorMapper.toDto(recuperator));
        assertThat(offerDTO.getLocationDTO()).isEqualTo(locationMapper.toDto(recuperator.getLocation()));
        assertThat(offerDTO.getDescription()).isEqualTo(offer.getDescription());
        assertThat(offerDTO.getTitle()).isEqualTo(offer.getTitle());
        assertThat(offerDTO.getAvailabilityBegin()).isEqualTo(offer.getAvailabilityBegin());
        assertThat(offerDTO.getAvailabilityEnd()).isEqualTo(offer.getAvailabilityEnd());
        assertThat(offerDTO.getDescription()).isEqualTo(offer.getDescription());
    }

    @Test
    public void testDTOToEntity() {
        //Given
        RecuperatorDTO recuperatorDTO = new RecuperatorDTO();
        LocationDTO locationDTO = new LocationDTO();
        OfferDTO offerDTO = new OfferDTO();
        recuperatorDTO.setId(1234L);
        recuperatorDTO.setName("Toto");
        recuperatorDTO.setPhoneNumber("13456475");
        locationDTO.setPostalCode("675679");
        locationDTO.setStreetAddress("AREGDGJFJDSDGNG");
        locationDTO.setCity("arhsgjdhgkfjlgkh");
        locationDTO.setId(132452L);
        recuperatorDTO.setLocationDTO(locationDTO);
        offerDTO.setId(149876L);
        offerDTO.setDescription("gergsghshsgfh");
        offerDTO.setIsCold(true);
        offerDTO.setAvailabilityBegin(ZonedDateTime.now());
        offerDTO.setAvailabilityEnd(ZonedDateTime.now());
        offerDTO.setTitle("TROREALARL");
        offerDTO.setLocationDTO(locationDTO);
        offerDTO.setRecuperatorDTO(recuperatorDTO);

        //When
        Offer offer = offerMapper.toEntity(offerDTO);

        //Then
        assertThat(offer).isNotNull();
        assertThat(offer.getRecuperator()).isEqualTo(recuperatorMapper.toEntity(recuperatorDTO));
        assertThat(offer.getLocation()).isEqualTo(locationMapper.toEntity(recuperatorDTO.getLocationDTO()));
        assertThat(offer.getDescription()).isEqualTo(offer.getDescription());
        assertThat(offer.getTitle()).isEqualTo(offer.getTitle());
        assertThat(offer.getAvailabilityBegin()).isEqualTo(offer.getAvailabilityBegin());
        assertThat(offer.getAvailabilityEnd()).isEqualTo(offer.getAvailabilityEnd());
        assertThat(offer.getDescription()).isEqualTo(offer.getDescription());
    }

}
