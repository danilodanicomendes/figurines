package com.danilomendes.figurines.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Provides the PROD schedulers needed.
 * (This is intended solely so tests flavor can mock this schedulers)
 *
 * Created by danilo on 09-11-2017.
 */
class SchedulerProvider {
    companion object {

        fun io(): Scheduler {
            return Schedulers.io()
        }

        fun ui(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }
}