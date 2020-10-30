package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.data.Constant.SP_UserId

/**
 * Created by Liam.Zheng on 2020/10/28
 *
 * Des:
 */
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    var userName by PreferenceUtils(SP_UserId, "")

    override fun initView(view: View) {
        if (userName.isEmpty()) {
            this.findNavController().navigate(R.id.action_splashFragment_to_login_nav_graph)
        } else {
            this.findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}
