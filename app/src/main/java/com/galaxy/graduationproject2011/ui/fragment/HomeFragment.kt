package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun initView(view: View) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}