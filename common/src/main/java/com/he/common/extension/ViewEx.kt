package com.he.common.extension

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Checkable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Px
import androidx.core.content.ContextCompat

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */

// 扩展点击事件属性(重复点击时长)
var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0

// 重复点击事件绑定
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


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

var View.scale: Float
    get() = Math.min(scaleX, scaleY)
    set(value) {
        scaleY = value
        scaleX = value
    }

fun View.addTopMargin(@Px marginInPx: Int) {
    (layoutParams as ViewGroup.MarginLayoutParams).topMargin = marginInPx
}

fun View.addBottomMargin(@Px marginInPx: Int) {
    (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = marginInPx
}


fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)


fun ImageView.tint(colorId: Int) {
    setColorFilter(ContextCompat.getColor(context, colorId), PorterDuff.Mode.SRC_IN)
}

fun TextView.leftIcon(drawableId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(
            ContextCompat.getDrawable(context, drawableId),
            null,
            null,
            null
    )
}

fun TextView.rightIcon(drawableId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(context, drawableId),
            null
    )
}
