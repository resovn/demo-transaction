package com.hcy308.transaction.util;


import com.hcy308.transaction.TxException;

import java.security.SecureRandom;


/**
 * @author simon
 */
public final class RandomUtils {

    private static volatile SecureRandom random;

    public static SecureRandom getRandom() {
        if (random == null) {
            synchronized (RandomUtils.class) {
                if (random == null) {
                    try {
                        random = SecureRandom.getInstance("SHA1PRNG");
                    } catch (Exception e) {
                        TxException ex = new TxException("failed to get Secure Random instance.");
                        ex.initCause(e);
                        throw ex;
                    }
                }
            }
        }

        return random;
    }


    public static long randomLong() {
        return getRandom().nextLong();
    }

}
