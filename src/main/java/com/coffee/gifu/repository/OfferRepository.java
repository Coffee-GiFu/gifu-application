
package com.coffee.gifu.repository;

import com.coffee.gifu.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Offer entity.
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o " +
            "ORDER BY case when o.recuperator is null then 1 else 0 end, " +
            "o.availabilityEnd ASC")
    List<Offer> searchCreatedOffer();

    @Query("SELECT o FROM Offer o " +
        "ORDER BY o.availabilityEnd ASC")
    List<Offer> searchChosenOffer();

    @Query(
        "SELECT o FROM Offer o " +
            "WHERE o.recuperator = NULL " +
            "AND o.availabilityEnd >= CURRENT_DATE " +
            "AND o.isCold = FALSE " +
            "ORDER BY o.availabilityEnd ASC ")
    List<Offer> searchAvailableOfferCold();

    @Query(value = "select distinct offer from Offer offer left join fetch offer.recuperators",
        countQuery = "select count(distinct offer) from Offer offer")
    Page<Offer> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct offer from Offer offer left join fetch offer.recuperators")
    List<Offer> findAllWithEagerRelationships();

    @Query("select offer from Offer offer left join fetch offer.recuperators where offer.id =:id")
    Optional<Offer> findOneWithEagerRelationships(@Param("id") Long id);

}
