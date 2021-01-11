package org.beer30.springcloud.simpleprocessor;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tsweets
 * Date: 1/9/21 - 3:20 PM
 */

@SpringBootApplication
@OpenAPIDefinition
public class SimpleProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleProcessorApplication.class, args);
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
