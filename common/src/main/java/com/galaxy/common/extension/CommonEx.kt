package com.galaxy.common.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
inline fun <reified T> Activity.start() {
    this.startActivity(Intent(this, T::class.java))
}

fun Activity.isPortrait() =
    this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun Fragment.isPortrait() =
    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

infix fun Activity.takeColor(colorId: Int) = ContextCompat.getColor(this, colorId)
infix fun Fragment.takeColor(colorId: Int) = ContextCompat.getColor(requireContext(), colorId)
infix fun Context.takeColor(colorId: Int) = ContextCompat.getColor(this, colorId)


val Context.displayMetrics: DisplayMetrics
    get() = resources.displayMetrics

fun Context.dpToPx(dp: Float) = (dp * this.displayMetrics.density + 0.5).toInt()

fun Context.pxToDp(px: Float) = (px / this.displayMetrics.density + 0.5).toInt()

fun Context.getScreenWidth(): Int = resources.displayMetrics.widthPixels

fun Context.getScreenHeight(): Int = resources.displayMetrics.heightPixels

fun Activity.getRealScreenWidth(): Int {
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}

fun getRealScreenHeight(windowManager: WindowManager): Int {
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getRealMetrics(dm)
    return dm.heightPixels
}
