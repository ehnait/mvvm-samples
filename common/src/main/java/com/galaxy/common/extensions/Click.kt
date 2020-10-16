package com.galaxy.common.extensions

import android.view.View
import android.widget.Checkable

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0


inline fun <T : View> T.singleClick(time: Long = 2000, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block.invoke(this)
        }
    }
}

fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 2000) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            onClickListener.onClick(this)
        }
    }
}