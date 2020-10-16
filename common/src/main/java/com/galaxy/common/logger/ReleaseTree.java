package com.galaxy.common.logger;

import android.util.Log;

import timber.log.Timber;

/**
 * 示例：
 * Timber.plant(new ReleaseTree());
 * Timber.v("release log test: Timber.v"); //do not acutal log.
 * Timber.i("release log test: Timber.i"); //do not acutal log.
 * Timber.d("release log test: Timber.d"); //do not acutal log.
 * Timber.w("release log test: Timber.w"); //do log.
 * Timber.e("release log test: Timber.e"); //do log.
 * Timber.wtf("release log test: Timber.wtf"); //do log.
 */
public class ReleaseTree extends Timber.DebugTree {
    @Override
    protected boolean isLoggable(String tag, int priority) {
        return priority == Log.WARN
                || priority == Log.ERROR
                || priority == Log.ASSERT;
    }

    @Override
    protected String createStackElementTag(StackTraceElement element) {
        return super.createStackElementTag(element) + ":" + element.getLineNumber();
    }
}