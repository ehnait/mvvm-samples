package com.galaxy.graduationproject2011.ui.activity

import android.os.Bundle
import com.galaxy.common.base.BaseActivity
import com.galaxy.common.extension.isInternetOn
import com.galaxy.graduationproject2011.R

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class LoginActivity : BaseActivity() {

    override fun getlayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        if (isInternetOn()) {

        } else {

        }
    }

}