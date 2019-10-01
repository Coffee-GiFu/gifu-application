package com.coffee.gifu.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InternalException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public InternalException() {
        super(ErrorConstants.INTERNAL_ERROR, "Une erreur interne inattendue est survenue", Status.INTERNAL_SERVER_ERROR);
    }
}
