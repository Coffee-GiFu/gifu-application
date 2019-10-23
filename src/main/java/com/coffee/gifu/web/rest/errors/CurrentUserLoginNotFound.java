package com.coffee.gifu.web.rest.errors;
import org.zalando.problem.AbstractThrowableProblem;

public class CurrentUserLoginNotFound extends AbstractThrowableProblem {
    private static final long serialVersionUID = 1L;

    public CurrentUserLoginNotFound(String message) {
        super(ErrorConstants.ENTITY_NOT_FOUND_TYPE, message);
    }
}
