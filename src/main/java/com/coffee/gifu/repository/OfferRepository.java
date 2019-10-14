
package com.coffee.gifu.repository;

import com.coffee.gifu.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.coffee.gifu.service.dto.OfferDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Offer entity.
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT Offer FROM Offer WHERE Offer.id = 2")
    List<OfferDTO> searchCreatedOffer();

    @Query("SELECT Offer FROM Offer WHERE Offer.id = 1")
    List<OfferDTO> searchChosenOffer();

    @Query(value = "select distinct offer from Offer offer left join fetch offer.recuperators",
        countQuery = "select count(distinct offer) from Offer offer")
    Page<Offer> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct offer from Offer offer left join fetch offer.recuperators")
    List<Offer> findAllWithEagerRelationships();

    @Query("select offer from Offer offer left join fetch offer.recuperators where offer.id =:id")
    Optional<Offer> findOneWithEagerRelationships(@Param("id") Long id);

    @Query(
        "SELECT Offer FROM Offer " +
        "WHERE Offer.recuperator = NULL " +
            "AND Offer.availabilityEnd >= CURRENT_DATE " +
            "AND :isColdFilter = Offer.isCold OR :isColdFilter = NULL " +
        "ORDER BY Offer.availabilityEnd ASC ")
    List<OfferDTO> searchAvailableOffer(Boolean isColdFilter);
}
