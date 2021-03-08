package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.ui.activity.MainActivity

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class Home2Fragment : BaseFragment<MainActivity>() {

    companion object {
        @JvmStatic
        fun newInstance() = Home2Fragment()
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun getlayoutId(): Int {
        return R.layout.fragment_home2
    }
}
