package com.galaxy.graduationproject2011.ui.activity

import android.os.Bundle
import com.galaxy.common.base.BaseActivity
import com.galaxy.common.extension.start
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.entity.Constant

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class SplashActivity : BaseActivity() {
    var userName by PreferenceUtils(Constant.SP_UserName, "")
    override fun getlayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (userName.isEmpty()) {
            start<LoginActivity>()
        } else {
            start<MainActivity>()
        }
    }

    override fun initData() {

    }


}