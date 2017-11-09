package com.danilomendes.figurines.util;

import android.util.Log;

/**
 * Created by danilo on 22-10-2017.
 */
public class L {
    private static final String TAG = "Figurines";

    public static void log(String message) {
        Log.d(TAG, ":" + Thread.currentThread().getName() + " " + message);
    }

    public static void log(String message, Throwable throwable) {
        Log.d(TAG, ":" + Thread.currentThread().getName() + " " + message, throwable);
    }
}
