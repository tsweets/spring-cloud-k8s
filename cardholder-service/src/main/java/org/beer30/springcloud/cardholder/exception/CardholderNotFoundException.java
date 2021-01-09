package org.beer30.springcloud.cardholder.exception;

public class CardholderNotFoundException extends RuntimeException {
    public CardholderNotFoundException(Long id) {
        super("A cardholder with id " + id + " was not found");
    }
}
