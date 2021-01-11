package org.beer30.springcloud.simpleprocessor.service.impl;

import org.beer30.springcloud.simpleprocessor.domain.Cardholder;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardholderDTO;
import org.beer30.springcloud.simpleprocessor.domain.mapper.CardholderMapper;
import org.beer30.springcloud.simpleprocessor.repository.CardholderRepository;
import org.beer30.springcloud.simpleprocessor.service.CardholderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;


/**
 * Service Implementation for managing Cardholder.
 */
@Service
@Transactional
public class CardholderServiceImpl implements CardholderService {

    private final Logger log = LoggerFactory.getLogger(CardholderServiceImpl.class);

    @Autowired
    private CardholderRepository cardholderRepository;

    @Inject
    private CardholderMapper cardholderMapper;

/*
    @Inject
    private CardholderSearchRepository cardholderSearchRepository;
*/

    /**
     * Save a cardholder.
     *
     * @return the persisted entity
     */
    public CardholderDTO save(CardholderDTO cardholderDTO) {
        log.debug("Request to save Cardholder : {}", cardholderDTO);
        Cardholder cardholder = cardholderMapper.cardholderDTOToCardholder(cardholderDTO);
        cardholder = cardholderRepository.save(cardholder);
        CardholderDTO result = cardholderMapper.cardholderToCardholderDTO(cardholder);
        return result;
    }

    /**
     * get all the cardholders.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Cardholder> findAll(Pageable pageable) {
        log.debug("Request to get all Cardholders");
        Page<Cardholder> result = cardholderRepository.findAll(pageable);
        return result;
    }

    /**
     * get one cardholder by id.
     *
     * @return the entity
     */
    @Transactional(readOnly = true)
    public CardholderDTO findOne(Long id) {
        log.debug("Request to get Cardholder : {}", id);
        Optional<Cardholder> cardholder = cardholderRepository.findById(id);
        if (cardholder.isEmpty()) {
            return null;
        }
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder.get());
        return cardholderDTO;
    }

    @Transactional(readOnly = true)
    public CardholderDTO findOneByExternalId(Integer envId, Long externalId) {
        log.debug("Request to get Cardholder External ID: {} ENV: {}", externalId, envId);
        Cardholder cardholder = cardholderRepository.findByExternalIdAndEnvId(externalId, envId);
        CardholderDTO cardholderDTO = cardholderMapper.cardholderToCardholderDTO(cardholder);
        return cardholderDTO;
    }

    /**
     * delete the  cardholder by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cardholder : {}", id);
        cardholderRepository.deleteById(id);
    }


}
