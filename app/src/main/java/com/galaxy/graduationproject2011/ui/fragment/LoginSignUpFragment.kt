package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.isInternetOn
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.singleClick
import com.galaxy.common.extension.start
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.entity.Constant
import com.galaxy.graduationproject2011.ui.activity.LoginActivity
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login_mobile_number.btnVerify
import kotlinx.android.synthetic.main.fragment_login_password.etPassword
import kotlinx.android.synthetic.main.fragment_login_password.etUsername
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginSignUpFragment : BaseFragment<LoginActivity>() {
    private var userNameSP by PreferenceUtils(Constant.SP_UserName, "")
    private var passWordSP by PreferenceUtils(Constant.SP_PassWord, "")

    override fun getlayoutId(): Int {
        return R.layout.fragment_sign_up
    }
    override fun initView() {
        tvTitle.text = getString(R.string.sign_up)

        ivBack.singleClick {
            findNavController().navigateUp()
        }

        btnVerify.singleClick {
            if (!requireActivity().isInternetOn()) {
                showShortToast(getString(R.string.the_network_not_connected))
                return@singleClick
            }
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val passwordAgain = etPasswordAgain.text.toString().trim()
            if (username.isEmpty()) {
                showShortToast(getString(R.string.username_cannot_be_empty))
                return@singleClick
            }
            if (password != passwordAgain) {
                showShortToast(getString(R.string.inconsistent_passwords))
                return@singleClick
            }
            userNameSP = username
            passWordSP = password
            Thread.sleep(1000)

            lifecycleScope.launch(Dispatchers.IO) {
                delay(1500)
                findNavController().navigateUp()
            }
        }
        etUsername.doAfterTextChanged {
            cheackButtonState()
        }
        etPassword.doAfterTextChanged {
            cheackButtonState()
        }
        etPasswordAgain.doAfterTextChanged {
            cheackButtonState()
        }
    }

    override fun initData() {

    }


    private fun cheackButtonState() {
        btnVerify.isEnabled =
            etUsername.text.isNotEmpty() && etPassword.text.isNotEmpty() && etPasswordAgain.text.isNotEmpty()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginSignUpFragment()
    }


}