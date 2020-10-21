package com.galaxy.graduationproject2011.ui.activity

import android.os.Bundle
import com.galaxy.common.base.BaseActivity
import com.galaxy.common.extension.singleClick
import com.galaxy.graduationproject2011.R
import com.mob.MobSDK
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class LoginActivity : BaseActivity(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobSDK.submitPolicyGrantResult(
            false,
            null
        )//https://www.mob.com/wiki/detailed?wiki=SMSSDK_for_Android_kuaisujicheng&id=23

    }

    override fun initView() {
        mobileLogin.singleClick {

        }

    }


}