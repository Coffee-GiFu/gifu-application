package com.coffee.gifu.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

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
    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @NotNull
    @Column(name = "is_cold", nullable = false)
    private Boolean isCold;

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

    @OneToOne(optional = false)    @NotNull

    @JoinColumn(unique = true)
    private Location location;

    @ManyToOne
    private Recuperator recuperator;

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

    public Boolean isIsCold() {
        return isCold;
    }

    public Offer isCold(Boolean isCold) {
        this.isCold = isCold;
        return this;
    }

    public void setIsCold(Boolean isCold) {
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

    public Recuperator getRecuperator() {
        return recuperator;
    }

    public Offer recuperator(Recuperator recuperator) {
        this.recuperator = recuperator;
        return this;
    }

    public void setRecuperator(Recuperator recuperator) {
        this.recuperator = recuperator;
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
