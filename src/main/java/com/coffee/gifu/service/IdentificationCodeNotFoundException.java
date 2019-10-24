package com.coffee.gifu.service;

public class IdentificationCodeNotFoundException extends RuntimeException {

    public IdentificationCodeNotFoundException() {
        super("Identification code was not found in government database!");
    }

}
