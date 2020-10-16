package com.galaxy.common.base

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.facebook.stetho.Stetho
import com.galaxy.common.logger.ReleaseTree
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Liam.Zheng on 2020/8/17
 *
 * Des:
 */
abstract class CommonBaseApplication : Application(), IApp {
    override fun onCreate() {
        super.onCreate()
        initLogger(debug())
        initCaoc(debug())
        initStetho(debug())
    }

    abstract fun debug(): Boolean

    /**
     * https://github.com/JakeWharton/timber
     *
     * @param isDebug
     */
    override fun initLogger(isDebug: Boolean) {
        if (isDebug) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    /**
     * Stetho is a sophisticated debug bridge for Android applications
     * http://facebook.github.io/stetho/
     *
     * @param isDebug
     */
    override fun initStetho(isDebug: Boolean) {
        if (isDebug) {
            Stetho.initializeWithDefaults(this)
        }
    }

    /**
     * Crash 捕捉界面 https://github.com/Ereza/CustomActivityOnCrash
     *
     * @param isDebug true 开启 false 关闭
     */
    override fun initCaoc(isDebug: Boolean) {
        CaocConfig.Builder.create().enabled(isDebug!!).apply()
    }


    companion object {
        private const val TAG = "CommonBaseApplication"
    }

}