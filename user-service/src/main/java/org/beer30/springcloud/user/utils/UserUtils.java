package org.beer30.springcloud.user.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author tsweets
 * Date: 1/9/21 - 7:33 PM
 */

public class UserUtils {
    public static String generateResetKey() {
        return RandomStringUtils.randomAlphanumeric(12);
    }

}
