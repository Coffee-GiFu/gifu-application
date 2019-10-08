package com.coffee.gifu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Recuperator.
 */
@Entity
@Table(name = "recuperator")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Recuperator implements Serializable {

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

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @NotNull
    private Location location;

    @OneToOne(optional = false)    @NotNull
    private Organisation association;

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

    public Recuperator name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Recuperator phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public Recuperator location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Organisation getAssociation() {
        return association;
    }

    public Recuperator association(Organisation organisation) {
        this.association = organisation;
        return this;
    }

    public void setAssociation(Organisation organisation) {
        this.association = organisation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recuperator)) {
            return false;
        }
        return id != null && id.equals(((Recuperator) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPhoneNumber(), getLocation(), getAssociation());
    }

    @Override
    public String toString() {
        return "Recuperator{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", location=" + location +
            ", association=" + association +
            '}';
    }
}
