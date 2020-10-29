package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import androidx.navigation.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.singleClick
import com.galaxy.graduationproject2011.R
import kotlinx.android.synthetic.main.fragment_login_host.*

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginHostFragment : BaseFragment(R.layout.fragment_login_host) {

    override fun initView(view: View) {
        btnLogin.singleClick {
            it.findNavController().navigate(R.id.action_loginHostFragment_to_loginMobileNumberFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginHostFragment()
    }
}