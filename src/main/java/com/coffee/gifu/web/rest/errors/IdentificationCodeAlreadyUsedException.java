package com.coffee.gifu.web.rest.errors;

public class IdentificationCodeAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public IdentificationCodeAlreadyUsedException() {
        super(ErrorConstants.IDENTIFICATION_CODE_ALREADY_USED_TYPE, "Identification Code already used!", "userManagement", "userexists");
    }
}
