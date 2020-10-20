package com.galaxy.graduationproject2011

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.galaxy.graduationproject2011.logger.ReleaseTree
import com.galaxy.graduationproject2011.logger.MultiTagTree
import timber.log.Timber

/**
 * Created by Liam.Zheng on 2020/9/23
 *
 * Des:
 */

class MyApplication : Application() {
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
        if (BuildConfig.DEBUG) {
            Timber.plant(MultiTagTree().addTag("Timber"))
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}