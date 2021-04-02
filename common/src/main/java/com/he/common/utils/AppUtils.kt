package com.he.common.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
object AppUtils {

    /**
     * 获取App包名
     *
     * @param context 上下文
     * @return App包名
     */
    @JvmStatic
    fun getAppPackageName(context: Context): String {
        return context.packageName
    }

    /**
     * 获取App名称
     *
     * @param context 上下文
     * @return App名称
     */
    @JvmStatic
    fun getAppName(context: Context): String? {
        return getAppName(context, context.packageName)
    }

    /**
     * 获取App名称
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App名称
     */
    @JvmStatic
    fun getAppName(context: Context, packageName: String?): String? {
        return if (packageName.isNullOrEmpty()) null else try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            pi?.applicationInfo?.loadLabel(pm)?.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取App图标
     *
     * @param context 上下文
     * @return App图标
     */
    @JvmStatic
    fun getAppIcon(context: Context): Drawable? {
        return getAppIcon(context, context.packageName)
    }

    /**
     * 获取App图标
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App图标
     */
    @JvmStatic
    fun getAppIcon(context: Context, packageName: String?): Drawable? {
        return if (packageName.isNullOrEmpty()) null else try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            pi?.applicationInfo?.loadIcon(pm)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取App路径
     *
     * @param context 上下文
     * @return App路径
     */
    @JvmStatic
    fun getAppPath(context: Context): String? {
        return getAppPath(context, context.packageName)
    }

    /**
     * 获取App路径
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App路径
     */
    @JvmStatic
    fun getAppPath(context: Context, packageName: String?): String? {
        return if (packageName.isNullOrEmpty()) null else try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            pi?.applicationInfo?.sourceDir
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取App版本号
     *
     * @param context 上下文
     * @return App版本号
     */
    @JvmStatic
    fun getAppVersionName(context: Context): String? {
        return getAppVersionName(context, context.packageName)
    }

    /**
     * 获取App版本号
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App版本号
     */
    @JvmStatic
    fun getAppVersionName(context: Context, packageName: String?): String? {
        return if (packageName.isNullOrEmpty()) null else try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            pi?.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取App版本码
     *
     * @param context 上下文
     * @return App版本码
     */
    @JvmStatic
    fun getAppVersionCode(context: Context): Int {
        return getAppVersionCode(context, context.packageName)
    }

    /**
     * 获取App版本码
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App版本码
     */
    @JvmStatic
    fun getAppVersionCode(context: Context, packageName: String?): Int {
        return if (packageName.isNullOrEmpty()) -1 else try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            pi?.versionCode ?: -1
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            -1
        }
    }

    /**
     * 判断App是否是Debug版本
     *
     * @param context 上下文
     * @return `true`: 是<br></br>`false`: 否
     */
    @JvmStatic
    fun isAppDebug(context: Context): Boolean {
        return isAppDebug(context, context.packageName)
    }

    /**
     * 判断App是否是Debug版本
     *
     * @param context     上下文
     * @param packageName 包名
     * @return `true`: 是<br></br>`false`: 否
     */
    @JvmStatic
    fun isAppDebug(context: Context, packageName: String?): Boolean {
        return if (packageName.isNullOrEmpty()) false else try {
            val pm = context.packageManager
            val ai = pm.getApplicationInfo(packageName, 0)
            ai.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

}