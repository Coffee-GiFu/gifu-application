package com.coffee.gifu.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.coffee.gifu.domain.Organisation} entity.
 */
public class OrganisationDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 140)
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @NotNull
    @Size(min = 5, max = 140)
    private String contactMail;

    private String logo;

    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Size(min = 10, max = 14)
    private String identificationCode;

    @NotNull
    private String type;


    private Long locationId;

    private String locationCity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganisationDTO organisationDTO = (OrganisationDTO) o;
        if (organisationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organisationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganisationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", contactMail='" + getContactMail() + "'" +
            ", logo='" + getLogo() + "'" +
            ", description='" + getDescription() + "'" +
            ", identificationCode='" + getIdentificationCode() + "'" +
            ", type='" + getType() + "'" +
            ", location=" + getLocationId() +
            ", location='" + getLocationCity() + "'" +
            "}";
    }
}
