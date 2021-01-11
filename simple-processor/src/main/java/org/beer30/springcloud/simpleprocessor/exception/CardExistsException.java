package org.beer30.springcloud.simpleprocessor.exception;

/**
 * @author tsweets
 * Date: 1/9/21 - 8:26 PM
 */

public class CardExistsException extends RuntimeException {
    public CardExistsException(String msg) {
        super(msg);
    }

}
