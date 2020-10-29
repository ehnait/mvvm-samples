package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.singleClick
import com.galaxy.graduationproject2011.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

/**
 * Created by Liam.Zheng on 2020/10/28
 *
 * Des:
 */
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    override fun initView(view: View) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}
