package com.danilomendes.figurines.util

import android.util.Log

/**
 * Created by danilo on 22-10-2017.
 */
object L {
    private val TAG = "Figurines"

    fun log(message: String) {
        Log.d(TAG, ":" + Thread.currentThread().name + " " + message)
    }

    fun log(message: String, throwable: Throwable) {
        Log.d(TAG, ":" + Thread.currentThread().name + " " + message, throwable)
    }
}
