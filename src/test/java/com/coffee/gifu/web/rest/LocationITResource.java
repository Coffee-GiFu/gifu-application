package com.coffee.gifu.web.rest;

import com.coffee.gifu.domain.Location;

import javax.persistence.EntityManager;

public class LocationITResource {

    private static final String DEFAULT_STREET_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STREET_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createEntity(EntityManager em) {
        return new Location()
            .streetAddress(DEFAULT_STREET_ADDRESS)
            .postalCode(DEFAULT_POSTAL_CODE)
            .city(DEFAULT_CITY);
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createUpdatedEntity(EntityManager em) {
        return new Location()
            .streetAddress(UPDATED_STREET_ADDRESS)
            .postalCode(UPDATED_POSTAL_CODE)
            .city(UPDATED_CITY);
    }
}
