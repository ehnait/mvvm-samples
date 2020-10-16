package com.galaxy.common.base

/**
 * Created by Liam.Zheng on 2020/8/17
 *
 * Des:
 */
internal interface IApp {
    fun initLogger(isDebug: Boolean)
    fun initStetho(isDebug: Boolean)
    fun initCaoc(isDebug: Boolean)

    /**
     * LeakCanary is a memory leak detection library for Android. https://square.github.io/leakcanary/
     * Upgrading to LeakCanary 2
     * There is no more code for default setup.
     *  Worth noting:
     *      1.LeakCanary auto installs itself
     *      2.LeakCanary analysis now runs in the main process so there is no need to call LeakCanary.isInAnalyzerProcess().
     */
    fun initLeakCanary() {}
}
