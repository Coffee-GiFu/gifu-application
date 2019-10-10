package com.coffee.gifu.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Organisation.
 */
@Entity
@Table(name = "organisation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3, max = 140)
    @Column(name = "name", length = 140, nullable = false)
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @NotNull
    @Size(min = 5, max = 140)
    @Column(name = "contact_mail", length = 140, nullable = false)
    private String contactMail;

    @Column(name = "logo")
    private String logo;

    @Size(min = 3, max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @NotNull
    @Size(min = 10, max = 14)
    @Column(name = "identification_code", length = 14, nullable = false, unique = true)
    private String identificationCode;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @NotNull
    private Location location;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Organisation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Organisation phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactMail() {
        return contactMail;
    }

    public Organisation contactMail(String contactMail) {
        this.contactMail = contactMail;
        return this;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getLogo() {
        return logo;
    }

    public Organisation logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public Organisation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public Organisation identificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
        return this;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getType() {
        return type;
    }

    public Organisation type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public Organisation location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organisation)) {
            return false;
        }
        return id != null && id.equals(((Organisation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Organisation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", contactMail='" + getContactMail() + "'" +
            ", logo='" + getLogo() + "'" +
            ", description='" + getDescription() + "'" +
            ", identificationCode='" + getIdentificationCode() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
