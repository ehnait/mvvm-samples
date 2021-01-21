package com.galaxy.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity() {
    protected abstract fun initView(savedInstanceState: Bundle?)
    private val layoutResID = contentLayoutId
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResID)
        initView(savedInstanceState)
    }
}