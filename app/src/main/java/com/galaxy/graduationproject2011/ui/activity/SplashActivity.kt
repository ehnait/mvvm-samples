package com.galaxy.graduationproject2011.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.galaxy.common.base.BaseActivity
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.start
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.graduationproject2011.MyApplication
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.entity.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class SplashActivity : BaseActivity() {
    private var firstStart by PreferenceUtils(Constant.SP_FIRST_START, true)
    override fun getlayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        if (firstStart) {
            showShortToast("FIRST START")
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(1500)
                if (MyApplication.instance.spUserName.isEmpty()) {
                    start<LoginActivity>()
                } else {
                    start<MainActivity>()
                }
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        firstStart = false
    }


}