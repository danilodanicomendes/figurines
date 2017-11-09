package com.danilomendes.figurines.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Provides schedulers for tests. All schedulers in tests are [Schedulers.trampoline]
 * so they'll run synchronously.
 *
 * Created by danilo on 09-11-2017.
 */
class SchedulerProvider {
    companion object {
        fun io(): Scheduler {
            return Schedulers.trampoline()
        }

        fun ui(): Scheduler {
            return Schedulers.trampoline()
        }
    }
}