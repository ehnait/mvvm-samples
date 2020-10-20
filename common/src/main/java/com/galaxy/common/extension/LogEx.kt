package com.galaxy.common.extension

import android.util.Log

/**
 * Created by Liam.Zheng on 2020/10/19
 *
 * Des:
 */

fun Any.logv(content: String?) {
    Log.v(this.javaClass.simpleName.toString(), content.toString())
}

fun Any.logd(content: String?) {
    Log.d(this.javaClass.simpleName.toString(), content.toString())
}

fun Any.logi(content: String?) {
    Log.i(this.javaClass.simpleName.toString(), content.toString())
}

fun Any.logw(content: String?) {
    Log.w(this.javaClass.simpleName.toString(), content.toString())
}

fun Any.loge(content: String?) {
    Log.e(this.javaClass.simpleName.toString(), content.toString())
}

fun Any.logwtf(content: String?) {
    Log.wtf(this.javaClass.simpleName.toString(), content.toString())
}
