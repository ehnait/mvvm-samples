package com.he.fastandroid.ui.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.he.common.base.BaseFragment
import com.he.common.extension.isInternetOn
import com.he.common.extension.showShortToast
import com.he.common.extension.singleClick
import com.he.fastandroid.R
import com.he.fastandroid.room.AppDatabase
import com.he.fastandroid.room.User
import com.he.fastandroid.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_login_mobile_number.btnVerify
import kotlinx.android.synthetic.main.fragment_login_password.etPassword
import kotlinx.android.synthetic.main.fragment_login_password.etUsername
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginSignUpFragment : BaseFragment<LoginActivity>() {
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
            if (password.length < 6) {
                showShortToast(getString(R.string.the_length_of_the_password_is_at_least_6))
                return@singleClick
            }
            if (password != passwordAgain) {
                showShortToast(getString(R.string.inconsistent_passwords))
                return@singleClick
            }
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(requireContext()).userDao().also {
                        val user = User(userName = username, userPassword = password)
                        it.insertAll(user)
                    }
                }
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