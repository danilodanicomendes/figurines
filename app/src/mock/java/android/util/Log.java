package android.util;

/**
 * Mocks the android.util.Log so we can use Log.d during tests. Otherwise we would not
 * be able to log because the Log class is a mock up when running unit tests.
 *
 * Created by danilo on 09-11-2017.
 */
public class Log {
    public static int d(String tag, String msg) {
        System.out.println("DEBUG: " + tag + ": " + msg);
        return 0;
    }

    public static int d(String tag, String msg, Throwable t) {
        System.out.println("DEBUG: " + tag + ": " + msg + "");
        return 0;
    }

    public static int i(String tag, String msg) {
        System.out.println("INFO: " + tag + ": " + msg);
        return 0;
    }

    public static int w(String tag, String msg) {
        System.out.println("WARN: " + tag + ": " + msg);
        return 0;
    }

    public static int e(String tag, String msg) {
        System.out.println("ERROR: " + tag + ": " + msg);
        return 0;
    }

    // add other methods if required...
}
