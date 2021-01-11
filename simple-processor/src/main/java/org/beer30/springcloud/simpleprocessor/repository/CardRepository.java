package org.beer30.springcloud.simpleprocessor.repository;

import org.beer30.springcloud.simpleprocessor.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Card entity.
 */
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByExternalIdAndEnvId(Long externalId, Integer envId);

}
