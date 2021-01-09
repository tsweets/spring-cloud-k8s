package org.beer30.springcloud.cardservice.service;

import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.cardservice.domain.Card;
import org.beer30.springcloud.cardservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public Card saveCard(Card card) {
        log.info("Saving Card: {}", card);
        return this.cardRepository.save(card);
    }

    public Card findCardById(Long id) {
        log.info("Find Card By ID: {}", id);
        return this.cardRepository.findCardById(id);
    }
}
