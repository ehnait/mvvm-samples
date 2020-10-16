package com.galaxy.graduationproject2011

import com.galaxy.common.base.CommonBaseApplication

/**
 * Created by Liam.Zheng on 2020/9/23
 *
 * Des:
 */
class MyApplication : CommonBaseApplication() {
    override fun debug(): Boolean {
        return BuildConfig.DEBUG
    }

}