package org.beer30.springcloud.simpleprocessor.controller;

import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.simpleprocessor.domain.Card;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardDTO;
import org.beer30.springcloud.simpleprocessor.domain.mapper.CardMapper;
import org.beer30.springcloud.simpleprocessor.exception.CardExistsException;
import org.beer30.springcloud.simpleprocessor.service.CardService;
import org.beer30.springcloud.simpleprocessor.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * REST controller for managing Card.
 */
@Slf4j
@RestController
@RequestMapping("/processor")
public class CardController {


    @Autowired
    private CardService cardService;

    @Inject
    private CardMapper cardMapper;

    /**
     * POST  /cards -> Create a new card.
     */
    @RequestMapping(value = "/cards",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> createCard(@Valid @RequestBody CardDTO cardDTO) throws URISyntaxException {
        log.debug("REST request to save Card : {}", cardDTO);
        if (cardDTO.getId() != null) {
            throw new CardExistsException("card:idexists:A new card cannot already have an ID: " + cardDTO);
        }
        CardDTO result = cardService.save(cardDTO);
        return new ResponseEntity<CardDTO>(result, HttpStatus.CREATED);
    }

    /**
     * PUT  /cards -> Updates an existing card.
     */
    @RequestMapping(value = "/cards",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> updateCard(@Valid @RequestBody CardDTO cardDTO) throws URISyntaxException {
        log.debug("REST request to update Card : {}", cardDTO);
        if (cardDTO.getId() == null) {
            return createCard(cardDTO);
        }
        CardDTO result = cardService.save(cardDTO);
        return new ResponseEntity<CardDTO>(result, HttpStatus.OK);
    }

    /**
     * GET  /cards -> get all the cards.
     */
    @RequestMapping(value = "/cards",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<CardDTO>> getAllCards(Pageable pageable)
            throws URISyntaxException {
        log.debug("REST request to get a page of Cards");
        Page<Card> page = cardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cards");
        return new ResponseEntity<List<CardDTO>>(page.getContent().stream()
                .map(cardMapper::cardToCardDTO)
                .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /cards/:id -> get the "id" card.
     */
    @RequestMapping(value = "/cards/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> getCard(@PathVariable Long id) {
        log.debug("REST request to get Card : {}", id);
        CardDTO cardDTO = cardService.findOne(id);
        return Optional.ofNullable(cardDTO)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /cards/:id -> delete the "id" card.
     */
    @RequestMapping(value = "/cards/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        log.debug("REST request to delete Card : {}", id);
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
