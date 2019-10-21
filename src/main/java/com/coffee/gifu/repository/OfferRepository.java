package com.coffee.gifu.repository;

import com.coffee.gifu.domain.Offer;
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
            "ORDER BY " +
            //"CASE WHEN o.recuperators IS NULL THEN 1 ELSE 0 END DESC," +//TODO selection par l'entreprise de l'assos ( offre fermer )
            "o.availabilityEnd ASC")
    List<Offer> searchCreatedOffer();

    @Query("SELECT o FROM Offer o " +
        "ORDER BY o.availabilityEnd ASC")
    List<Offer> searchChosenOffer();

    @Query("select distinct offer from Offer offer left join fetch offer.recuperators")
    List<Offer> findAllWithEagerRelationships();

    @Query("select offer from Offer offer left join fetch offer.recuperators where offer.id =:id")
    Optional<Offer> findOneWithEagerRelationships(@Param("id") Long id);

    @Query(
        "SELECT o FROM Offer o " +
            "WHERE " +
            //"o.recuperator = NULL AND " +//TODO selection par l'entreprise de l'assos ( offre fermer )
            "o.availabilityEnd >= CURRENT_DATE " +
            "AND o.isCold = FALSE " +
            "ORDER BY o.availabilityEnd ASC ")
    List<Offer> searchAvailableOfferNotCold();

    @Query(
        "SELECT o FROM Offer o " +
            "WHERE " +
            //"o.recuperator = NULL AND " +//TODO selection par l'entreprise de l'assos ( offre fermer )
            "o.availabilityEnd >= CURRENT_DATE  " +
            "ORDER BY o.availabilityEnd ASC ")
    List<Offer> searchAvailableOffer();
}
