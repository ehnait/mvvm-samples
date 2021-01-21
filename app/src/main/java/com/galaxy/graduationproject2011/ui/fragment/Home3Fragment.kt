package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R
import timber.log.Timber

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class Home3Fragment : BaseFragment(R.layout.fragment_home3) {

    override fun initView(view: View) {
        Timber.d("initView:Home3Fragment")
    }

    companion object {
        @JvmStatic
        fun newInstance() = Home3Fragment()
    }
}

