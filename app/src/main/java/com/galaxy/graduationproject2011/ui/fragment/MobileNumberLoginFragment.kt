package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class MobileNumberLoginFragment(override val layoutId: Int = R.layout.fragment_mobile_number_login) :
    BaseFragment() {

    override fun initView(view: View) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = MobileNumberLoginFragment()
    }
}