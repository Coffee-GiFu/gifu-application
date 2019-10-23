package com.coffee.gifu.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;

public class EnterpriseNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public EnterpriseNotFoundException(String message) {
        super(ErrorConstants.ENTITY_NOT_FOUND_TYPE, message);
    }
}
