package com.galaxy.common.logger

import android.util.Log
import timber.log.Timber

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 * Timber.plant(new ReleaseTree());
 * Timber.v("release log test: Timber.v"); //do not acutal log.
 * Timber.i("release log test: Timber.i"); //do not acutal log.
 * Timber.d("release log test: Timber.d"); //do not acutal log.
 * Timber.w("release log test: Timber.w"); //do log.
 * Timber.e("release log test: Timber.e"); //do log.
 * Timber.wtf("release log test: Timber.wtf"); //do log.
*/
class ReleaseTree : Timber.DebugTree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == Log.WARN || priority == Log.ERROR || priority == Log.ASSERT
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        return super.createStackElementTag(element) + ":" + element.lineNumber
    }
}