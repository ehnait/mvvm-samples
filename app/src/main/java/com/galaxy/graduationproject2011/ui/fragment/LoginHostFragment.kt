package com.galaxy.graduationproject2011.ui.fragment

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.singleClick
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.ui.activity.LoginActivity
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
                showShortToast("当前设备没有安装QQ")
            }
        }

        ivLoginWechat.singleClick {
            if (UmengClient.isAppInstalled(this.requireContext(), Platform.WECHAT)) {
                loginUmengClient(Platform.WECHAT)
            } else {
                showShortToast("当前设备没有安装微信")
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
        showShortToast("第三方登录出错：${t?.message}")
    }

    override fun onCancel(platform: Platform?) {
        showShortToast("取消第三方登录：")
    }

}