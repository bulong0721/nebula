package com.rhea.common.util;

import java.util.Base64;

/**
 * @author xubulong
 * @version V1.0
 */
public class Base64Util {

    /**
     * @param binaryData
     * @return
     */
    public static String encode(final byte[] binaryData) {
        return Base64.getEncoder().encodeToString(binaryData);
    }

    /**
     * @param encoded
     * @return
     */
    public static byte[] decode(final String encoded) {
        return Base64.getDecoder().decode(encoded);
    }
}
