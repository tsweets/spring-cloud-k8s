package org.beer30.springcloud.simpleprocessor.service;

import org.beer30.springcloud.simpleprocessor.domain.Cardholder;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardholderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Cardholder.
 */
public interface CardholderService {

    /**
     * Save a cardholder.
     *
     * @return the persisted entity
     */
    CardholderDTO save(CardholderDTO cardholderDTO);

    /**
     * get all the cardholders.
     *
     * @return the list of entities
     */
    Page<Cardholder> findAll(Pageable pageable);

    /**
     * get the "id" cardholder.
     *
     * @return the entity
     */
    CardholderDTO findOne(Long id);

    /**
     * delete the "id" cardholder.
     */
    void delete(Long id);

    /**
     * search for the cardholder corresponding
     * to the query.
     */
    //  public List<CardholderDTO> search(String query);

    CardholderDTO findOneByExternalId(Integer envId, Long externalId);

}
