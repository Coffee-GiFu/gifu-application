package com.coffee.gifu.web.rest.wrapper;

import com.coffee.gifu.service.dto.OrganisationDTO;
import com.coffee.gifu.web.rest.vm.ManagedUserVM;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Objects;

public class CreateUserRequest implements Serializable {

    private @Valid ManagedUserVM managedUserVM;

    private @Valid OrganisationDTO organisationDTO;

    public CreateUserRequest() {}

    public CreateUserRequest(@Valid ManagedUserVM managedUserVM, @Valid OrganisationDTO organisationDTO) {
        this.managedUserVM = managedUserVM;
        this.organisationDTO = organisationDTO;
    }

    public ManagedUserVM getManagedUserVM() {
        return managedUserVM;
    }

    public void setUserDTO(@Valid ManagedUserVM managedUserVM) {
        this.managedUserVM = managedUserVM;
    }

    public OrganisationDTO getOrganisationDTO() {
        return organisationDTO;
    }

    public void setOrganisationDTO(@Valid OrganisationDTO organisationDTO) {
        this.organisationDTO = organisationDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserRequest)) return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return Objects.equals(getManagedUserVM(), that.getManagedUserVM()) &&
            Objects.equals(getOrganisationDTO(), that.getOrganisationDTO());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManagedUserVM(), getOrganisationDTO());
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
            "managedUserVM=" + managedUserVM +
            ", organisationDTO=" + organisationDTO +
            '}';
    }
}
