package com.example.lib_common.binding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


@BindingAdapter("bindLoading")
fun SwipeRefreshLayout. bindingLoading( isLoading: Boolean) {
    Log.d("bindingLoading", " isLoading = $isLoading")
    isRefreshing = isLoading
    if (!isLoading)isEnabled = false
}