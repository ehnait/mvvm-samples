package com.he.fastandroid.ui.fragment

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.he.common.base.BaseFragment
import com.he.common.extension.showShortToast
import com.he.common.extension.singleClick
import com.he.fastandroid.R
import com.he.fastandroid.ui.activity.LoginActivity
import com.hjq.umeng.Platform
import com.hjq.umeng.UmengClient
import com.hjq.umeng.UmengLogin
import kotlinx.android.synthetic.main.fragment_login_host.*

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginHostFragment : BaseFragment<LoginActivity>(), UmengLogin.OnLoginListener {


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
            if (UmengClient.isAppInstalled(this.requireContext(), Platform.QQ)) {
                loginUmengClient(Platform.QQ)
            } else {
                showShortToast(getString(R.string.qq_is_not_installed_on_the_current_device))
            }
        }

        ivLoginWechat.singleClick {
            if (UmengClient.isAppInstalled(this.requireContext(), Platform.WECHAT)) {
                loginUmengClient(Platform.WECHAT)
            } else {
                showShortToast(getString(R.string.wechat_is_not_installed_on_the_current_device))
            }
        }
    }

    override fun initData() {
    }

    private fun loginUmengClient(platform: Platform) {
        UmengClient.login(requireActivity(), platform, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 友盟登录回调
        UmengClient.onActivityResult(requireActivity(), requestCode, resultCode, data)
    }

    override fun onSucceed(platform: Platform?, data: UmengLogin.LoginData?) {
        showShortToast("昵称：${data?.name}  性别：${data?.sex}")
        showShortToast("id：${data?.id}")
        showShortToast("token：${data?.token}")
    }

    override fun onError(platform: Platform?, t: Throwable?) {
        showShortToast("${getString(R.string.third_party_login_error)}：${t?.message}")
    }

    override fun onCancel(platform: Platform?) {
        showShortToast(getString(R.string.third_party_login_cancel))
    }

}