package com.coffee.gifu.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.coffee.gifu.domain.Recuperator} entity.
 */
public class RecuperatorDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 140)
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    private String phoneNumber;

    private OrganisationDTO association;

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

    public OrganisationDTO getAssociation() {
        return association;
    }

    public void setAssociation(OrganisationDTO association) {
        this.association = association;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecuperatorDTO recuperatorDTO = (RecuperatorDTO) o;
        if (recuperatorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recuperatorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecuperatorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", association=" + getAssociation() +
            "}";
    }
}
