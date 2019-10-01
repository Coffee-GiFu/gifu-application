package com.coffee.gifu.web.rest;

import com.coffee.gifu.security.AuthoritiesConstants;
import com.coffee.gifu.service.DataGouvRNAService;
import com.coffee.gifu.service.DataGouvSIRETService;
import javassist.NotFoundException;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.coffee.gifu.domain.Offer}.
 */
@RestController
@RequestMapping("/api")
public class ExternalResource {

    private final Logger log = LoggerFactory.getLogger(ExternalResource.class);

    private static final String ENTITY_NAME = "external";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataGouvRNAService rnaService;
    private final DataGouvSIRETService siretService;

    public ExternalResource(DataGouvRNAService rnaService, DataGouvSIRETService siretService) {
        this.rnaService = rnaService;
        this.siretService = siretService;
    }

    /**
     * {@code GET  /external/RNA/:code} : get the RNA "id".
     *
     * @param rnaCode the RNA code of the association to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the JSONObject,
     * or with status {@code 404 (Not Found)} (NotFoundException).
     * or with status {@code 500 (Internal Error)} (InterruptedException, ParseException, IOException).
     */
    @GetMapping("/external/RNA/{rnaCode}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<JSONObject> getRNA(@PathVariable String rnaCode) throws InterruptedException, NotFoundException, ParseException, IOException {
        log.debug("REST request to get RNA : {}", rnaCode);
        JSONObject json = rnaService.callApi(rnaCode);
        return ResponseEntity.ok().body(json);
    }

    /**
     * {@code GET  /external/SIRET/:code} : get the SIRET "code".
     *
     * @param siretCode the SIRET code of the company to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the JSONObject,
     * or with status {@code 404 (Not Found)} (NotFoundException).
     * or with status {@code 500 (Internal Error)} (InterruptedException, ParseException, IOException).
     */
    @GetMapping("/external/SIRET/{siretCode}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<JSONObject> getSIRET(@PathVariable String siretCode) throws InterruptedException, NotFoundException, ParseException, IOException {
        log.debug("REST request to get SIRET : {}", siretCode);
        JSONObject json = siretService.callApi(siretCode);
        return ResponseEntity.ok().body(json);
    }
}
