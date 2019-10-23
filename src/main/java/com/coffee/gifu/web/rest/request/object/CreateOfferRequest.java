package com.coffee.gifu.web.rest.request.object;

import com.coffee.gifu.service.dto.LocationDTO;
import org.checkerframework.common.value.qual.BoolVal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;

public class CreateOfferRequest implements Serializable {


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsCold() {
        return isCold;
    }

    public void setIsCold(boolean cold) {
        isCold = cold;
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
}
