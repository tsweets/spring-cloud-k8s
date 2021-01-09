package org.beer30.springcloud.cardholder.repository;

import org.beer30.springcloud.cardholder.domain.Cardholder;
import org.springframework.data.repository.CrudRepository;

public interface CardholderRepository extends CrudRepository<Cardholder, Long> {

    Cardholder findCardholderById(Long cardholderId);
}
