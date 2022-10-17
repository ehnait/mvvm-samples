package com.example.lib_common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView(savedInstanceState)
        startObserve()
    }

    override fun onDestroy() {
        //KeyboardUtils.hideSoftInput(window)
        super.onDestroy()
    }

    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun startObserve()

}