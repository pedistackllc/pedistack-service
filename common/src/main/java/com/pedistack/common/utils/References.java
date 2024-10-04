package com.pedistack.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class References {

    public static String createReference(int referenceLength) {
        return RandomStringUtils.randomAlphanumeric(referenceLength);
    }

}
