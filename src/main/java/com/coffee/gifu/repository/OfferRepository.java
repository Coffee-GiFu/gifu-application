
package com.coffee.gifu.repository;
import com.coffee.gifu.domain.Offer;
import com.coffee.gifu.service.dto.OfferDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Offer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o WHERE Offer.id = 2")
    List<OfferDTO> searchCreatedOffer();

    @Query("SELECT o FROM Offer o WHERE Offer.id = 1")
    List<OfferDTO> searchChosenOffer();

    @Query(
        "SELECT o FROM Offer o " +
        "WHERE o.recuperator = NULL " +
            "AND o.availabilityEnd >= CURRENT_DATE " +
            "AND :isColdFilter = o.isCold OR :isColdFilter = NULL " +
        "ORDER BY o.availabilityEnd ASC ")
    List<OfferDTO> searchAvailableOffer(Boolean isColdFilter);
}
