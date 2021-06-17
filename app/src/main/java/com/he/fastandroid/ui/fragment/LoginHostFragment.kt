package com.he.fastandroid.ui.fragment

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.he.common.base.BaseFragment
import com.he.common.extension.showShortToast
import com.he.common.extension.singleClick
import com.he.fastandroid.R
import com.he.fastandroid.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_login_host.*

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginHostFragment : BaseFragment<LoginActivity>() {


    companion object {
        @JvmStatic
        fun newInstance() = LoginHostFragment()
    }


    override fun getlayoutId(): Int {
        return R.layout.fragment_login_host
    }

    override fun initView() {
        btnLoginWithOTP.singleClick {
            findNavController().navigate(R.id.action_loginHostFragment_to_loginMobileNumberFragment)
        }
        btnLoginWithPassWord.singleClick {
            findNavController().navigate(R.id.action_loginHostFragment_to_loginPassWordFragment)
        }
        ivLoginQQ.singleClick {
            showShortToast(getString(R.string.qq_is_not_installed_on_the_current_device))
        }

        ivLoginWechat.singleClick {
            showShortToast(getString(R.string.wechat_is_not_installed_on_the_current_device))
        }
    }

    override fun initData() {
    }
}