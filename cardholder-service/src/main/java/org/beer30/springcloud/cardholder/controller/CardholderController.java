package org.beer30.springcloud.cardholder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.cardholder.domain.Cardholder;
import org.beer30.springcloud.cardholder.exception.CardholderNotFoundException;
import org.beer30.springcloud.cardholder.service.CardholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cardholders")
@Slf4j
public class CardholderController {
    @Autowired
    private CardholderService cardholderService;

    @PostMapping("/")
    public Cardholder saveCardholder(@Valid @RequestBody Cardholder cardholder) {
        log.info("REST: Save Cardholder: {}", cardholder);
        return cardholderService.saveCardholder(cardholder);
    }

    @Operation(summary = "Get Cardholder by ID")
    @ApiResponses(
            value =  {
                    @ApiResponse(responseCode = "200", description = "Found the Cardholder", content = {@Content(mediaType = "application/json")})
            }
    )
    @GetMapping("/{id}")
    public Cardholder findCardholderById(@PathVariable("id") Long cardholderId) {
        log.info("REST: Get Cardholder By ID: {}", cardholderId);
        return cardholderService.findCardholderById(cardholderId).orElseThrow(() -> new CardholderNotFoundException(cardholderId));
    }
 }
