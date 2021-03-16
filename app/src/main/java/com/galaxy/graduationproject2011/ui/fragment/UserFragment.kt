package com.galaxy.graduationproject2011.ui.fragment

import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.ui.activity.MainActivity

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class UserFragment : BaseFragment<MainActivity>() {

    override fun getlayoutId(): Int {
        return R.layout.fragment_user
    }
    override fun initView() {
    }

    override fun initData() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }
}

