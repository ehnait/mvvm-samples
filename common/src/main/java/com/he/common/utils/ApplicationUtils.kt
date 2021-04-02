package com.he.common.utils

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import java.lang.reflect.InvocationTargetException

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des: getApplication()
 */
@SuppressLint("PrivateApi")
object ApplicationUtils {
    private const val isDebug = true
    private const val TAG = "ApplicationUtil"
    private var sApp: Application? = null

    fun getApplication(): Application {
        return sApp ?: getApplicationByReflect().also { sApp = it }
        ?: throw NullPointerException("reflect failed.")
    }

    private fun getApplicationByReflect(): Application? {
        try {
            val activityThreadClass =
                Class.forName("android.app.ActivityThread")
            val thread: Any? = getActivityThread()
            val app = activityThreadClass.getMethod("getApplication").invoke(thread)
            return app as? Application
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getActivityThread(): Any? {
        return getActivityThreadInActivityThreadStaticField()
            ?: getActivityThreadInActivityThreadStaticMethod()
    }

    private fun getActivityThreadInActivityThreadStaticField(): Any? {
        return try {
            val activityThreadClass =
                Class.forName("android.app.ActivityThread")
            val sCurrentActivityThreadField =
                activityThreadClass.getDeclaredField("sCurrentActivityThread")
            sCurrentActivityThreadField.isAccessible = true
            sCurrentActivityThreadField[null]
        } catch (e: Exception) {
            if (isDebug) {
                Log.d(TAG, "getActivityThreadInActivityThreadStaticField: " + e.message)
            }
            null
        }
    }

    private fun getActivityThreadInActivityThreadStaticMethod(): Any? {
        return try {
            val activityThreadClass =
                Class.forName("android.app.ActivityThread")
            activityThreadClass.getMethod("currentActivityThread").invoke(null)
        } catch (e: java.lang.Exception) {
            if (isDebug) {
                Log.d(TAG, "getActivityThreadInActivityThreadStaticMethod: " + e.message)
            }
            null
        }
    }
}