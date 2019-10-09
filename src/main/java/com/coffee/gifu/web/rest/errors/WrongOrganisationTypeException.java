package com.coffee.gifu.web.rest.errors;

public class WrongOrganisationTypeException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public WrongOrganisationTypeException() {
        super(ErrorConstants.WRONG_ORGANISATION_TYPE, "Wrong Organisation Type!", "userManagement", "wrongorganisationtype");
    }
}
