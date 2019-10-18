package com.coffee.gifu.web.rest.wrapper;

import com.coffee.gifu.service.dto.OrganisationDTO;
import com.coffee.gifu.service.dto.UserDTO;

import java.io.Serializable;
import java.util.Objects;

public class CreateUserRequest implements Serializable {

    private UserDTO userDTO;

    private OrganisationDTO organisationDTO;

    public CreateUserRequest() {}

    public CreateUserRequest(UserDTO userDTO, OrganisationDTO organisationDTO) {
        this.userDTO = userDTO;
        this.organisationDTO = organisationDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public OrganisationDTO getOrganisationDTO() {
        return organisationDTO;
    }

    public void setOrganisationDTO(OrganisationDTO organisationDTO) {
        this.organisationDTO = organisationDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserRequest)) return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return Objects.equals(getUserDTO(), that.getUserDTO()) &&
            Objects.equals(getOrganisationDTO(), that.getOrganisationDTO());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserDTO(), getOrganisationDTO());
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
            "userDTO=" + userDTO +
            ", organisationDTO=" + organisationDTO +
            '}';
    }
}
