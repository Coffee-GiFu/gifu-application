package com.coffee.gifu.web.rest;

import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.domain.User;
import com.coffee.gifu.repository.RecuperatorRepository;
import com.coffee.gifu.security.AuthoritiesConstants;
import com.coffee.gifu.security.SecurityUtils;
import com.coffee.gifu.service.OrganisationService;
import com.coffee.gifu.service.RecuperatorService;
import com.coffee.gifu.service.UserService;
import com.coffee.gifu.service.dto.OrganisationDTO;
import com.coffee.gifu.web.rest.errors.BadRequestAlertException;
import com.coffee.gifu.service.dto.RecuperatorDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;


/**
 * REST controller for managing {@link com.coffee.gifu.domain.Recuperator}.
 */
@RestController
@RequestMapping("/api")
public class RecuperatorResource {

    private final Logger log = LoggerFactory.getLogger(RecuperatorResource.class);

    private static final String ENTITY_NAME = "recuperator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserService userService;
    private final OrganisationService organisationService;
    private final RecuperatorRepository recuperatorRepository;

    public RecuperatorResource(RecuperatorService recuperatorService, OrganisationService organisationService,
                         UserService userService, RecuperatorRepository recuperatorRepository) {
        this.recuperatorService = recuperatorService;
        this.organisationService = organisationService;
        this.userService = userService;
        this.recuperatorRepository = recuperatorRepository;
    }
    private final RecuperatorService recuperatorService;

    private Optional<User> getUser() {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin.isEmpty()) {
            //throw new CurrentUserLoginNotFound("currentUserLogin is not found");
        }
        Optional<User> userWithAuthorities = userService.getUserWithAuthoritiesByLogin(currentUserLogin.get());
        if (userWithAuthorities.isEmpty()) {
            //throw new CurrentUserLoginNotFound("userWithAuthoritiesByLogin is not found");
        }
        return userWithAuthorities;
    }
    private Optional<OrganisationDTO> getUserOrganisation() {
        Optional<User> userWithAuthorities = this.getUser();
        Optional<OrganisationDTO> optionalOrganisationDTO = organisationService.findOne(userWithAuthorities.get().getOrganisationID());
        if (optionalOrganisationDTO.isEmpty()) {
            //throw new EnterpriseNotFoundException("Organisation not found for this id " + optionalOrganisationDTO.get().getId());
        }
        return optionalOrganisationDTO;
    }
    private boolean allowForEdit(Long id){
        Optional<Recuperator> recuperator = recuperatorRepository.findById(id);
        return recuperator.get().getAssociation().getId() == getUserOrganisation().get().getId();
    }

    /**
     * {@code POST  /recuperators} : Create a new recuperator.
     *
     * @param recuperatorDTO the recuperatorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recuperatorDTO, or with status {@code 400 (Bad Request)} if the recuperator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recuperators")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ASSOCIATION + "\")")
    public ResponseEntity<RecuperatorDTO> createRecuperator(@Valid @RequestBody RecuperatorDTO recuperatorDTO) throws URISyntaxException {
        log.debug("REST request to save Recuperator : {}", recuperatorDTO);
        if (recuperatorDTO.getId() != null || !allowForEdit(recuperatorDTO.getId())) {
            throw new BadRequestAlertException("A new recuperator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecuperatorDTO result = recuperatorService.save(recuperatorDTO);
        return ResponseEntity.created(new URI("/api/recuperators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recuperators} : Updates an existing recuperator.
     *
     * @param recuperatorDTO the recuperatorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recuperatorDTO,
     * or with status {@code 400 (Bad Request)} if the recuperatorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recuperatorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recuperators")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ASSOCIATION + "\")")
    public ResponseEntity<RecuperatorDTO> updateRecuperator(@Valid @RequestBody RecuperatorDTO recuperatorDTO) throws URISyntaxException {
        log.debug("REST request to update Recuperator : {}", recuperatorDTO);
        if (recuperatorDTO.getId() == null || !allowForEdit(recuperatorDTO.getId())) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecuperatorDTO result = recuperatorService.save(recuperatorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recuperatorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recuperators} : get all the recuperators.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recuperators in body.
     */
    @GetMapping("/recuperators")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public List<RecuperatorDTO> getAllRecuperators() {
        log.debug("REST request to get all Recuperators");
        return recuperatorService.findAll();
    }

    /**
     * {@code GET  /recuperators/:id} : get the "id" recuperator.
     *
     * @param id the id of the recuperatorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recuperatorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recuperators/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<RecuperatorDTO> getRecuperator(@PathVariable Long id) {
        log.debug("REST request to get Recuperator : {}", id);
        Optional<RecuperatorDTO> recuperatorDTO = recuperatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recuperatorDTO);
    }

    /**
     * {@code DELETE  /recuperators/:id} : delete the "id" recuperator.
     *
     * @param id the id of the recuperatorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recuperators/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ASSOCIATION + "\")")
    public ResponseEntity<Void> deleteRecuperator(@PathVariable Long id) {
        log.debug("REST request to delete Recuperator : {}", id);
        recuperatorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
