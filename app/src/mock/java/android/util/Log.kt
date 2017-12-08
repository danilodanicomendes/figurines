package android.util

/**
 * Mocks the android.util.Log so we can use Log.d during tests. Otherwise we would not
 * be able to log because the Log class is a mock up when running unit tests.
 *
 * Created by danilo on 09-11-2017.
 */
object Log {
    fun d(tag: String, msg: String): Int {
        println("DEBUG: $tag: $msg")
        return 0
    }

    fun d(tag: String, msg: String, t: Throwable): Int {
        println("DEBUG: $tag: $msg")
        return 0
    }

    fun i(tag: String, msg: String): Int {
        println("INFO: $tag: $msg")
        return 0
    }

    fun w(tag: String, msg: String): Int {
        println("WARN: $tag: $msg")
        return 0
    }

    fun e(tag: String, msg: String): Int {
        println("ERROR: $tag: $msg")
        return 0
    }
}
