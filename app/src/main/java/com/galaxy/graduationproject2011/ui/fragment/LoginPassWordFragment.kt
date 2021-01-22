package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.singleClick
import com.galaxy.common.extension.start
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.entity.Constant
import com.galaxy.graduationproject2011.ui.activity.AppBaseActivity
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login_mobile_number.btnVerify
import kotlinx.android.synthetic.main.fragment_login_password.*
import kotlinx.android.synthetic.main.layout_toolbar.*


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginPassWordFragment : BaseFragment(R.layout.fragment_login_password) {
    private val userNameSP by PreferenceUtils(Constant.SP_UserName, "")
    private val passWordSP by PreferenceUtils(Constant.SP_PassWord, "")

    override fun initView(view: View) {
        tvTitle.text = getString(R.string.log_in)

        ivBack.singleClick {
            findNavController().navigateUp()
        }

        tvGoToRegister.singleClick {
            findNavController().navigate(R.id.action_loginPassWordFragment_to_loginSignUpFragment)
        }
        btnVerify.singleClick {
            if (!(requireActivity() as AppBaseActivity).isInternetOn()) {
                showShortToast(getString(R.string.the_network_not_connected))
                return@singleClick
            }
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (userNameSP == username && passWordSP == password) {
                requireActivity().start<MainActivity>()
            } else {
                showShortToast(getString(R.string.the_password_is_incorrect))
            }
        }
        etUsername.doAfterTextChanged {
            cheackButtonState()
        }
        etPassword.doAfterTextChanged {
            cheackButtonState()
        }

    }

    private fun cheackButtonState() {
        btnVerify.isEnabled = etUsername.text.isNotEmpty() && etPassword.text.isNotEmpty()
    }

    companion object {
        private const val TAG = "LoginPassWordFragment"

        @JvmStatic
        fun newInstance() = LoginPassWordFragment()
    }
}