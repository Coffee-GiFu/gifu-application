package com.coffee.gifu.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String COMPANY = "ROLE_COMPANY";

    public static final String ASSOCIATION = "ROLE_ASSOCIATION";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
