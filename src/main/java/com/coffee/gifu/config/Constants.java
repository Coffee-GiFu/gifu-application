package com.coffee.gifu.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "fr";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String RNA_API_PATH = "https://entreprise.data.gouv.fr/api/rna/v1/id/";
    public static final String SIRET_API_PATH = "https://entreprise.data.gouv.fr/api/sirene/v1/siret/";

    private Constants() {
    }
}
