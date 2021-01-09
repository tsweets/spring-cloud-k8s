package org.beer30.springcloud.cardholder.service;

import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.cardholder.domain.Card;
import org.beer30.springcloud.cardholder.domain.Cardholder;
import org.beer30.springcloud.cardholder.repository.CardholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class CardholderService {

    @Autowired
    private CardholderRepository cardholderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Cardholder saveCardholder(Cardholder cardholder) {
        log.info("Saving Cardholder: {}", cardholder);
        return cardholderRepository.save(cardholder);
    }

    public Optional<Cardholder> findCardholderById(Long cardholderId) {
        log.info("Find Cardholder by Id: {}", cardholderId);

        Cardholder cardholder = cardholderRepository.findCardholderById(cardholderId);
        if (cardholder != null) {
            Card card = restTemplate.getForObject("http://CARD-SERVICE/cards/" + cardholderId, Card.class);
            cardholder.setCard(card);
            return Optional.of(cardholder);
        } else {
            return Optional.empty();
        }
    }

}
