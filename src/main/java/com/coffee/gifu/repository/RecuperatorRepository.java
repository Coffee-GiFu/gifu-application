package com.coffee.gifu.repository;

import com.coffee.gifu.domain.Recuperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Recuperator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecuperatorRepository extends JpaRepository<Recuperator, Long> {

}
