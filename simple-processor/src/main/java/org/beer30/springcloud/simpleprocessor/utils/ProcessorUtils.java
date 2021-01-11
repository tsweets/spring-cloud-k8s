package org.beer30.springcloud.simpleprocessor.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author tsweets
 * Date: 12/24/15
 * Time: 12:57 PM
 */
public class ProcessorUtils {
    public static String TEST_BIN = "123123";


    public static String createTestCardNumber() {
        StringBuffer PREFIX = new StringBuffer(TEST_BIN);
        String randomNumberString = RandomStringUtils.randomNumeric(10);
        return PREFIX.append(randomNumberString).toString();
    }

}
