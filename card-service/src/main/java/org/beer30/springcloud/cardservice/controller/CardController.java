package org.beer30.springcloud.cardservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.cardservice.domain.Card;
import org.beer30.springcloud.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@Slf4j
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/")
    public Card saveCard(@RequestBody Card card) {
        log.info("REST: Save Card: {}", card);
        return cardService.saveCard(card);
    }

    @Operation(summary = "Get Card by ID")
    @ApiResponses(
            value =  {
                    @ApiResponse(responseCode = "200", description = "Found the Card", content = {@Content(mediaType = "application/json")})
            }
    )
    @GetMapping("/{id}")
    public Card findCardById(@PathVariable("id") Long cardId) {
        log.info("REST: Get Card By ID: {}", cardId);
        return cardService.findCardById(cardId);
    }
}
