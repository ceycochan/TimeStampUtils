package com.nshane.timestamputils;

/**
 * Created by da on 2017-12-8.
 */

public class Utils {
    public static final long ONE_DAY_MILLISECOND = 1000 * 60 * 60 * 24L;

    public static int getOpenInterval(long interval) {
        /**
         * while the unit of time of the return value is a millisecond
         */
        long current = System.currentTimeMillis();
        long difference = current - interval;
        return (int) (difference / ONE_DAY_MILLISECOND);
    }
}
