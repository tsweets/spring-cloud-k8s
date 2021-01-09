package org.beer30.springcloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/cardholderServiceFallback")
    public String cardholderServiceFallback() {
        return "Error Trying to Reach Cardholder API";
    }

    @GetMapping("/cardServiceFallback")
    public String cardServiceFallback() {
        return "Error Trying to Reach Card API";
    }
}
