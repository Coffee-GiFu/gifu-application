package com.coffee.gifu.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.coffee.gifu.domain.Offer} entity.
 */
public class OfferDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 10, max = 255)
    private String description;

    private boolean isCold;

    @NotNull
    private ZonedDateTime availabilityBegin;

    @NotNull
    private ZonedDateTime availabilityEnd;

    @NotNull
    @Size(min = 10, max = 100)
    private String title;

    @NotNull
    private LocationDTO locationDTO;

    private Set<RecuperatorDTO> recuperatorDTOs = new HashSet<>();

    @NotNull
    private OrganisationDTO enterprise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsCold() {
        return isCold;
    }

    public void setIsCold(Boolean isCold) {
        this.isCold = isCold;
    }

    public ZonedDateTime getAvailabilityBegin() {
        return availabilityBegin;
    }

    public void setAvailabilityBegin(ZonedDateTime availabilityBegin) {
        this.availabilityBegin = availabilityBegin;
    }

    public ZonedDateTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public void setAvailabilityEnd(ZonedDateTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public Set<RecuperatorDTO> getRecuperatorDTOs() {
        return recuperatorDTOs;
    }

    public void setRecuperatorDTOs(Set<RecuperatorDTO> recuperatorDTOs) {
        this.recuperatorDTOs = recuperatorDTOs;
    }

    public OrganisationDTO getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(OrganisationDTO enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OfferDTO offerDTO = (OfferDTO) o;
        if (offerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), offerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", isCold='" + isIsCold() + "'" +
            ", availabilityBegin='" + getAvailabilityBegin() + "'" +
            ", availabilityEnd='" + getAvailabilityEnd() + "'" +
            ", title='" + getTitle() + "'" +
            ", location=" + getLocationDTO() +
            ", organisation=" + getEnterprise() +
            "}";
    }
}
