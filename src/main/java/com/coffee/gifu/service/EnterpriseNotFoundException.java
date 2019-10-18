package com.coffee.gifu.service;

public class EnterpriseNotFoundException extends RuntimeException {

    public EnterpriseNotFoundException(String errorMsg){
        super(errorMsg);
    };

}
