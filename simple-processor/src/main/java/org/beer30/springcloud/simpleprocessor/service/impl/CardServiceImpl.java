package org.beer30.springcloud.simpleprocessor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.simpleprocessor.domain.Card;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardDTO;
import org.beer30.springcloud.simpleprocessor.domain.mapper.CardMapper;
import org.beer30.springcloud.simpleprocessor.repository.CardRepository;
import org.beer30.springcloud.simpleprocessor.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Service Implementation for managing Card.
 */
@Slf4j
@Service
@Transactional
public class CardServiceImpl implements CardService {


    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;


    /**
     * Save a card.
     *
     * @return the persisted entity
     */
    public CardDTO save(CardDTO cardDTO) {
        log.debug("Request to save Card : {}", cardDTO);
        Card card = cardMapper.cardDTOToCard(cardDTO);
        card = cardRepository.save(card);
        CardDTO result = cardMapper.cardToCardDTO(card);
        // cardSearchRepository.save(card);
        return result;
    }

    /**
     * get all the cards.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Card> findAll(Pageable pageable) {
        log.debug("Request to get all Cards");
        Page<Card> result = cardRepository.findAll(pageable);
        return result;
    }

    /**
     * get one card by id.
     *
     * @return the entity
     */
    @Transactional(readOnly = true)
    public CardDTO findOne(Long id) {
        log.debug("Request to get Card : {}", id);
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            return null;
        }
        CardDTO cardDTO = cardMapper.cardToCardDTO(card.get());
        return cardDTO;
    }

    @Transactional(readOnly = true)
    public CardDTO findByExternalId(Integer envId, Long externalId) {
        log.debug("Request to get Card by External ID: {} - ENV {}", externalId, envId);
        Card card = cardRepository.findByExternalIdAndEnvId(externalId, envId);
        CardDTO cardDTO = cardMapper.cardToCardDTO(card);
        return cardDTO;
    }

    /**
     * delete the  card by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Card : {}", id);
        cardRepository.deleteById(id);
        //  cardSearchRepository.delete(id);
    }


}
