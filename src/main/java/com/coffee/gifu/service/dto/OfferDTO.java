package com.coffee.gifu.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.coffee.gifu.domain.Offer} entity.
 */
public class OfferDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 10, max = 255)
    private String description;

    @NotNull
    private Boolean isCold;

    @NotNull
    private ZonedDateTime availabilityBegin;

    @NotNull
    private ZonedDateTime availabilityEnd;

    @NotNull
    @Size(min = 10, max = 100)
    private String title;


    private Long locationId;

    private Long recuperatorId;

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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getRecuperatorId() {
        return recuperatorId;
    }

    public void setRecuperatorId(Long recuperatorId) {
        this.recuperatorId = recuperatorId;
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
            ", location=" + getLocationId() +
            ", recuperator=" + getRecuperatorId() +
            "}";
    }
}
