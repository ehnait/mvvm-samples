package com.galaxy.common.extension

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Function to display a [Toast] on the screen for a [Toast.LENGTH_SHORT] period of time.
 * </br>
 * The user can provide [gravity] to position the [Toast] at a desired place on the screen,
 * and here, the user does not have to provide [xOffset] and [yOffSet] if there to be set to zero.
 */
fun AppCompatActivity.showShortToast(
    message: String,
    gravity: Int? = null,
    xOffset: Int? = null,
    yOffSet: Int? = null
) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    gravity?.let { toast?.setGravity(it, xOffset ?: 0, yOffSet ?: 0) }
    toast.show()
}

/**
 * Function to display a [Toast] on the screen for a [Toast.LENGTH_SHORT] period of time.
 * </br>
 * The user can provide [gravity] to position the [Toast] at a desired place on the screen,
 * and here, the user does not have to provide [xOffset] and [yOffSet] if there to be set to zero.
 */
fun Fragment.showShortToast(
    message: String,
    gravity: Int? = null,
    xOffset: Int? = null,
    yOffSet: Int? = null
) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    gravity?.let { toast?.setGravity(it, xOffset ?: 0, yOffSet ?: 0) }
    toast.show()
}

fun Context.showShortToast(
    message: String,
    gravity: Int? = null,
    xOffset: Int? = null,
    yOffSet: Int? = null
) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    gravity?.let { toast?.setGravity(it, xOffset ?: 0, yOffSet ?: 0) }
    toast.show()
}


fun AppCompatActivity.showLongToast(
    message: String,
    gravity: Int? = null,
    xOffset: Int? = null,
    yOffSet: Int? = null
) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    gravity?.let { toast?.setGravity(it, xOffset ?: 0, yOffSet ?: 0) }
    toast.show()
}

/**
 * Function to display a [Toast] on the screen for a [Toast.LENGTH_LONG] period of time.
 * </br>
 * The user can provide [gravity] to position the [Toast] at a desired place on the screen,
 * and here, the user does not have to provide [xOffset] and [yOffSet] if there to be set to zero.
 */
fun Fragment.showLongToast(
    message: String,
    gravity: Int? = null,
    xOffset: Int? = null,
    yOffSet: Int? = null
) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    gravity?.let { toast?.setGravity(it, xOffset ?: 0, yOffSet ?: 0) }
    toast.show()
}

fun Context.showLongToast(
    message: String,
    gravity: Int? = null,
    xOffset: Int? = null,
    yOffSet: Int? = null
) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    gravity?.let { toast?.setGravity(it, xOffset ?: 0, yOffSet ?: 0) }
    toast.show()
}