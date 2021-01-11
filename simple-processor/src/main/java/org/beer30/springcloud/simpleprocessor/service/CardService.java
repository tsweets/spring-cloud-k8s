package org.beer30.springcloud.simpleprocessor.service;

import org.beer30.springcloud.simpleprocessor.domain.Card;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Card.
 */
public interface CardService {

    /**
     * Save a card.
     *
     * @return the persisted entity
     */
    CardDTO save(CardDTO cardDTO);

    /**
     * get all the cards.
     *
     * @return the list of entities
     */
    Page<Card> findAll(Pageable pageable);

    /**
     * get the "id" card.
     *
     * @return the entity
     */
    CardDTO findOne(Long id);

    CardDTO findByExternalId(Integer envId, Long externalId);

    /**
     * delete the "id" card.
     */
    void delete(Long id);

}
