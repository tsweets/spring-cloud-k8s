package org.beer30.springcloud.cardservice.repository;

import org.beer30.springcloud.cardservice.domain.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long > {
    public Card findCardById(Long id);
}
