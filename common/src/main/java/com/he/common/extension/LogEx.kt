package com.he.common.extension

import android.util.Log

/**
 * Created by Liam.Zheng on 2020/10/19
 *
 * Des:
 */

fun getLogDebug(): Boolean {
//    return BuildConfig.DEBUG
    return true
}

fun Any.logv(content: String?) {
    if (getLogDebug())
        Log.v(this.javaClass.simpleName, content.toString())
}

fun Any.logd(content: String?) {
    if (getLogDebug())
        Log.d(this.javaClass.simpleName, content.toString())
}

fun Any.logi(content: String?) {
    if (getLogDebug())
        Log.i(this.javaClass.simpleName, content.toString())
}

fun Any.logw(content: String?) {
    if (getLogDebug())
        Log.w(this.javaClass.simpleName, content.toString())
}

fun Any.loge(content: String?, tr: Throwable? = null) {
    if (getLogDebug())
        Log.e(this.javaClass.simpleName, content.toString(), tr)
}

fun Any.logwtf(content: String?) {
    if (getLogDebug())
        Log.wtf(this.javaClass.simpleName, content.toString())
}
