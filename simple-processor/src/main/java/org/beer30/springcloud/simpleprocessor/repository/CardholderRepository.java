package org.beer30.springcloud.simpleprocessor.repository;

import org.beer30.springcloud.simpleprocessor.domain.Cardholder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Cardholder entity.
 */
public interface CardholderRepository extends JpaRepository<Cardholder, Long> {

    Cardholder findByExternalIdAndEnvId(Long externalId, Integer envId);

}
