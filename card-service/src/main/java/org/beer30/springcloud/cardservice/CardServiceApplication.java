package org.beer30.springcloud.cardservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class CardServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class,args);
    }
}
