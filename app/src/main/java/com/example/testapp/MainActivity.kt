package com.example.testapp

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.lib_common.base.BaseActivity
import com.example.lib_common.di.DefaultDispatcher
import com.example.lib_common.di.IoDispatcher
import com.example.lib_common.ext.launchAndCollectIn
import com.example.lib_common.ext.launchRepeatOnCreated
import com.example.lib_common.ext.launchRepeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun startObserve() {

    }
}