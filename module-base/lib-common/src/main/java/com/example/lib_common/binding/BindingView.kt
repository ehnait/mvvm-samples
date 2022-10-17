package com.example.lib_common.binding

import android.app.Activity
import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Created by luyao
 * on 2020/3/3 14:29
 */
@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("isInvisible")
fun View.bindIsInvisible(isInvisible: Boolean) {
    visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("bindFinish")
fun bindingFinish(view: View, finish: Boolean) {
    val ctx = view.context
    if (ctx is Activity && finish) {
        view.setOnClickListener { ctx.finish() }
    }
}
