package com.he.common.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
inline fun <reified T> Context.start(intent: Intent = Intent()) {
    intent.setClass(this, T::class.java)
    if (this !is Activity) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    this.startActivity(intent)
}

inline fun <reified F : Fragment> Context.newFragment(): F {
    return if (this is FragmentActivity) {
        supportFragmentManager.fragmentFactory.instantiate(
            this.classLoader,
            F::class.java.name
        ) as F
    } else {
        Fragment.instantiate(this, F::class.java.name) as F
    }
}

fun Activity.isPortrait() =
    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun Fragment.isPortrait() =
    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun Context.takeColor(colorId: Int) = ContextCompat.getColor(this, colorId)

fun Context.dpToPx(dp: Float) = (dp * resources.displayMetrics.density + 0.5).toInt()

fun Context.pxToDp(px: Float) = (px / resources.displayMetrics.density + 0.5).toInt()

fun Context.getScreenWidth(): Int = resources.displayMetrics.widthPixels

fun Context.getScreenHeight(): Int = resources.displayMetrics.heightPixels

fun Any.getRealScreenWidth(windowManager: WindowManager): Int {
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}

fun Any.getRealScreenHeight(windowManager: WindowManager): Int {
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getRealMetrics(dm)
    return dm.heightPixels
}


fun Activity.isInternetOn(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var networkInfo: NetworkInfo? = null
    try {
        networkInfo = manager.activeNetworkInfo
    } catch (e: Exception) {

    }
    return networkInfo != null && networkInfo.isConnected
}
