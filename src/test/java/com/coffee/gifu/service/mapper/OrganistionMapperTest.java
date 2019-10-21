package com.coffee.gifu.service.mapper;

import com.coffee.gifu.domain.Location;
import com.coffee.gifu.domain.Organisation;
import com.coffee.gifu.domain.OrganisationType;
import com.coffee.gifu.service.dto.LocationDTO;
import com.coffee.gifu.service.dto.OrganisationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganistionMapperTest {

    @Autowired
    private OrganisationMapper organisationMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testEntityToDTO() {
        //Given
        Organisation organisation = new Organisation();
        Location location = new Location();
        location.setId(1L);
        location.setPostalCode("675679");
        location.setStreetAddress("AREGDGJFJDSDGNG");
        location.setCity("arhsgjdhgkfjlgkh");
        organisation.setId(1L);
        organisation.setDescription("Test");
        organisation.setContactMail("Test");
        organisation.setIdentificationCode("0123456789");
        organisation.setLogo("Test");
        organisation.setName("Test");
        organisation.setPhoneNumber("0123456789");
        organisation.setType("ENTERPRISE");
        organisation.setLocation(location);

        //When
        OrganisationDTO organisationDTO = organisationMapper.toDto(organisation);

        //Then
        assertThat(organisationDTO).isNotNull();
        assertThat(organisationDTO.getId()).isEqualTo(organisation.getId());
        assertThat(organisationDTO.getLocationDTO()).isEqualTo(locationMapper.toDto(organisation.getLocation()));
        assertThat(organisationDTO.getDescription()).isEqualTo(organisation.getDescription());
        assertThat(organisationDTO.getLogo()).isEqualTo(organisation.getLogo());
        assertThat(organisationDTO.getIdentificationCode()).isEqualTo(organisation.getIdentificationCode());
        assertThat(organisationDTO.getContactMail()).isEqualTo(organisation.getContactMail());
        assertThat(organisationDTO.getName()).isEqualTo(organisation.getName());
        assertThat(organisationDTO.getType().toString()).isEqualTo(organisation.getType());
    }

    @Test
    public void testDTOToEntity() {
        //Given
        OrganisationDTO organisationDTO = new OrganisationDTO();
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(1L);
        locationDTO.setPostalCode("675679");
        locationDTO.setStreetAddress("AREGDGJFJDSDGNG");
        locationDTO.setCity("arhsgjdhgkfjlgkh");
        organisationDTO.setId(1L);
        organisationDTO.setDescription("Test");
        organisationDTO.setContactMail("Test");
        organisationDTO.setIdentificationCode("0123456789");
        organisationDTO.setLogo("Test");
        organisationDTO.setName("Test");
        organisationDTO.setPhoneNumber("0123456789");
        organisationDTO.setType(OrganisationType.ENTERPRISE);
        organisationDTO.setLocationDTO(locationDTO);

        //When
        Organisation organisation = organisationMapper.toEntity(organisationDTO);

        //Then
        assertThat(organisation).isNotNull();
        assertThat(organisation.getId()).isEqualTo(organisationDTO.getId());
        assertThat(organisation.getLocation()).isEqualTo(locationMapper.toEntity(organisationDTO.getLocationDTO()));
        assertThat(organisation.getDescription()).isEqualTo(organisationDTO.getDescription());
        assertThat(organisation.getLogo()).isEqualTo(organisationDTO.getLogo());
        assertThat(organisation.getIdentificationCode()).isEqualTo(organisationDTO.getIdentificationCode());
        assertThat(organisation.getContactMail()).isEqualTo(organisationDTO.getContactMail());
        assertThat(organisation.getName()).isEqualTo(organisationDTO.getName());
        assertThat(organisation.getType()).isEqualTo(organisationDTO.getType().toString());
    }

}
