package com.coffee.gifu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Offer.
 */
@Entity
@Table(name = "offer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 10, max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_cold", nullable = false)
    private boolean isCold;

    @NotNull
    @Column(name = "availability_begin", nullable = false)
    private ZonedDateTime availabilityBegin;

    @NotNull
    @Column(name = "availability_end", nullable = false)
    private ZonedDateTime availabilityEnd;

    @NotNull
    @Size(min = 10, max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private Location location;
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "offer_recuperators",
            joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recuperators_id", referencedColumnName = "id"))
    private Set<Recuperator> recuperators = new HashSet<>();

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    @NotNull
    private Organisation organisation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Offer description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsCold() {
        return isCold;
    }

    public Offer isCold(boolean isCold) {
        this.isCold = isCold;
        return this;
    }

    public void setIsCold(boolean isCold) {
        this.isCold = isCold;
    }

    public ZonedDateTime getAvailabilityBegin() {
        return availabilityBegin;
    }

    public Offer availabilityBegin(ZonedDateTime availabilityBegin) {
        this.availabilityBegin = availabilityBegin;
        return this;
    }

    public void setAvailabilityBegin(ZonedDateTime availabilityBegin) {
        this.availabilityBegin = availabilityBegin;
    }

    public ZonedDateTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public Offer availabilityEnd(ZonedDateTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
        return this;
    }

    public void setAvailabilityEnd(ZonedDateTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public String getTitle() {
        return title;
    }

    public Offer title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Location getLocation() {
        return location;
    }

    public Offer location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Recuperator> getRecuperators() {
        return recuperators;
    }

    public Offer recuperators(Set<Recuperator> recuperators) {
        this.recuperators = recuperators;
        return this;
    }

    public void setRecuperators(Set<Recuperator> recuperators) {
        this.recuperators = recuperators;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public Offer organisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        return id != null && id.equals(((Offer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + getId() +
                ", description='" + getDescription() + "'" +
                ", isCold='" + isIsCold() + "'" +
                ", availabilityBegin='" + getAvailabilityBegin() + "'" +
                ", availabilityEnd='" + getAvailabilityEnd() + "'" +
                ", title='" + getTitle() + "'" +
                "}";
    }
}
