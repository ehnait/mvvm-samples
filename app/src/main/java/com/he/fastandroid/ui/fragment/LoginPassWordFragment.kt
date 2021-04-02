package com.he.fastandroid.ui.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.he.common.base.BaseFragment
import com.he.common.extension.isInternetOn
import com.he.common.extension.showShortToast
import com.he.common.extension.singleClick
import com.he.common.extension.start
import com.he.fastandroid.MyApplication
import com.he.fastandroid.R
import com.he.fastandroid.room.AppDatabase
import com.he.fastandroid.ui.activity.LoginActivity
import com.he.fastandroid.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login_mobile_number.btnVerify
import kotlinx.android.synthetic.main.fragment_login_password.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.coroutines.*


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginPassWordFragment : BaseFragment<LoginActivity>() {

    override fun getlayoutId(): Int {
        return R.layout.fragment_login_password
    }

    override fun initView() {
        tvTitle.text = getString(R.string.log_in)

        ivBack.singleClick {
            findNavController().navigateUp()
        }

        tvGoToRegister.singleClick {
            findNavController().navigate(R.id.action_loginPassWordFragment_to_loginSignUpFragment)
        }
        btnVerify.singleClick {
            if (!requireActivity().isInternetOn()) {
                showShortToast(getString(R.string.the_network_not_connected))
                return@singleClick
            }
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            lifecycleScope.launch {
                val result = withContext(Dispatchers.IO) {
                    var resultFlag = false
                    AppDatabase.getInstance(requireContext()).userDao().getAll().forEach {
                        if (username == it.userName && password == it.userPassword) {
                            resultFlag = true
                            return@forEach
                        }
                    }
                    resultFlag
                }

                if (result) {
                    MyApplication.instance.spUserName = username
                    delay(1500)
                    requireActivity().start<MainActivity>()
                    requireActivity().finish()
                } else {
                    showShortToast(getString(R.string.the_password_is_incorrect))
                }
            }

        }
        etUsername.doAfterTextChanged {
            cheackButtonState()
        }
        etPassword.doAfterTextChanged {
            cheackButtonState()
        }
    }

    override fun initData() {

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