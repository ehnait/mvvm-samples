package com.example.lib_common.ext

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Px
import androidx.core.content.ContextCompat


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