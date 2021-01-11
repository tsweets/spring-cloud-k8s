package org.beer30.springcloud.simpleprocessor.exception;

/**
 * @author tsweets
 * Date: 1/9/21 - 8:26 PM
 */

public class CardholderExistsException extends RuntimeException {
    public CardholderExistsException(String msg) {
        super(msg);
    }

}
