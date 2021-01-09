package org.beer30.springcloud.cardholder.controller;


import org.beer30.springcloud.cardholder.service.CardholderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardholderController.class)
public class CardholderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardholderService cardholderService;

    @Test
    public void notFoundCardholderTest() throws Exception {
        Long cardholderId = 3l;
        given(cardholderService.findCardholderById(cardholderId)).willReturn(Optional.empty());

        mockMvc.perform(get("/cardholders/" + cardholderId).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string("A cardholder with id " + cardholderId + " was not found"));

    }

}
