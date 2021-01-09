package org.beer30.springcloud.cardholder.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CardholderValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        Cardholder cardholder = new Cardholder();
        cardholder.setId(1l);
        cardholder.setFirstName("TestFName");
        cardholder.setLastName("TestLName");

        Set<ConstraintViolation<Cardholder>> violations = validator.validate(cardholder);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenMissingFnameThenValidationFails() {
        Cardholder cardholder = new Cardholder();
        cardholder.setId(1l);
        cardholder.setLastName("TestLName");

        Set<ConstraintViolation<Cardholder>> violations = validator.validate(cardholder);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("First Name is required");
    }
}
