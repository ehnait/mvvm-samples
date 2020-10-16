package com.galaxy.common.logger;

/**
 * 官方示例 https://github.com/JakeWharton/timber
 * Not a real crash reporting library!
 */

public final class FakeCrashLibrary {
    public static void log(int priority, String tag, String message) {
        //  add log entry to circular buffer.
    }

    public static void logWarning(Throwable t) {
        //  report non-fatal warning.
    }

    public static void logError(Throwable t) {
        //  report non-fatal error.
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }
}