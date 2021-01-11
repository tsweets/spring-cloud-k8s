package org.beer30.springcloud.simpleprocessor.exception;

import org.beer30.springcloud.simpleprocessor.domain.dto.AddCardMSG;

/**
 * @author tsweets
 * Date: 1/9/21 - 8:26 PM
 */

public class CreateCardException extends RuntimeException {
    public CreateCardException(AddCardMSG msg) {
        super("Error Adding Card to Cardholder: " + msg.toString());
    }

}
