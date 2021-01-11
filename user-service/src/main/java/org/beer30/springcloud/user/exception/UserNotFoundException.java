package org.beer30.springcloud.user.exception;

/**
 * @author tsweets
 * Date: 1/9/21 - 8:26 PM
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("A user with id " + id + " was not found");
    }

}
