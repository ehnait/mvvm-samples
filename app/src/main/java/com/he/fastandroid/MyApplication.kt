package com.he.fastandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.he.common.utils.PreferenceUtils
import com.he.fastandroid.entity.Constant

/**
 * Created by Liam.Zheng on 2020/9/23
 *
 * Des:
 */

class MyApplication : Application() {
    var spUserName by PreferenceUtils(Constant.SP_USER_NAME, "")

    companion object {
        private const val TAG = "MyApplication"

        @get:Synchronized
        lateinit var instance: MyApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}