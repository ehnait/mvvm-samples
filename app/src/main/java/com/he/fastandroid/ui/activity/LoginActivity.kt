package com.he.fastandroid.ui.activity

import android.os.Bundle
import com.he.common.base.BaseActivity
import com.he.common.extension.isInternetOn
import com.he.fastandroid.R

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