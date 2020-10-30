package com.galaxy.graduationproject2011.ui.fragment

import android.os.CountDownTimer
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import cn.smssdk.SMSSDK.*
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.singleClick
import com.galaxy.common.extension.visible
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.common.utils.RegUtils
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.data.Constant
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login_mobile_number.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.json.JSONObject
import timber.log.Timber


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginMobileNumberFragment : BaseFragment(R.layout.fragment_login_mobile_number) {
    var userName by PreferenceUtils(Constant.SP_UserId, "")
    private lateinit var eventhandler: EventHandler
    private lateinit var countDownTimer: CountDownTimer

    override fun initView(view: View) {
        val activity = requireActivity() as MainActivity
        initCountDown()
        initSMSSDK()
        ivBack.singleClick {
            findNavController().popBackStack()
        }
        tvSend.singleClick {
            if (!activity.isNetworkConnected()) {
                showShortToast(getString(R.string.the_network_not_connected))
                return@singleClick
            }
            val phoneNumber = etPhone.text.toString().trim()
            if (RegUtils.isMobileSimple(phoneNumber)) {
                getVerificationCode(DEFAULT_COUNTRY_ID, phoneNumber)
            } else {
                showShortToast(getString(R.string.please_enter_the_correct_phone_number))
            }
        }
        tvAudio.singleClick {
            if (!activity.isNetworkConnected()) {
                showShortToast(getString(R.string.the_network_not_connected))
                return@singleClick
            }
            val phoneNumber = etPhone.text.toString().trim()
            if (RegUtils.isMobileSimple(phoneNumber)) {
                getVoiceVerifyCode(DEFAULT_COUNTRY_ID, phoneNumber)
            } else {
                showShortToast(getString(R.string.please_enter_the_correct_phone_number))
            }
        }
        btnVerify.singleClick {
            if (!activity.isNetworkConnected()) {
                showShortToast(getString(R.string.the_network_not_connected))
                return@singleClick
            }
            val phoneNumber = etPhone.text.toString().trim()
            val phoneCode = etCode.text.toString().trim()
            submitVerificationCode(DEFAULT_COUNTRY_ID, phoneNumber, phoneCode)
        }
        etPhone.doAfterTextChanged {
            cheackButtonState()
        }
        etCode.doAfterTextChanged {
            cheackButtonState()
        }
    }

    private fun cheackButtonState() {
        btnVerify.isEnabled = etPhone.text.isNotEmpty() && etCode.text.isNotEmpty()
    }

    private fun initCountDown() {
        countDownTimer = object : CountDownTimer(30 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (tvSend.isEnabled) tvSend.isEnabled = false
                tvSend.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                tvSend.isEnabled = true
                tvSend.text = getString(R.string.reget)
                tvAudio.visible()
            }
        }
    }

    private fun initSMSSDK() {
        eventhandler = object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any?) {
                if (result == RESULT_COMPLETE) {//回调完成
                    when (event) {
                        EVENT_GET_VERIFICATION_CODE, SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE -> {//获取验证码
                            requireActivity().runOnUiThread {
                                countDownTimer.start()
                            }
                        }
                        EVENT_SUBMIT_VERIFICATION_CODE -> {//提交验证码成功
                            val phoneNumber = etPhone.text.toString().trim()
                            userName = phoneNumber
                            findNavController().navigate(R.id.action_loginMobileNumberFragment_to_homeFragment)
                        }
                    }
                } else {
                    data?.let { processError(it) }
                }
            }
        }
        registerEventHandler(eventhandler)
    }

    private fun processError(data: Any) {
        try {
            val throwable: Throwable = data as Throwable
            throwable.printStackTrace()
            val obj = JSONObject(throwable.message.toString())
            val des: String = obj.optString("detail")
            val status = obj.optInt("status")
            if (des.isNotEmpty()) {
                showShortToast("des: $des status: $status")
                return
            }
        } catch (e: Exception) {
            Timber.wtf(e)
        }
        showShortToast(getString(R.string.the_network_is_abnormal_please_try_again_later))
    }


    override fun onDestroy() {
        unregisterEventHandler(eventhandler)
        countDownTimer.cancel()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "LoginMobileNumberFragme"
        private const val DEFAULT_COUNTRY_ID = "86"

        @JvmStatic
        fun newInstance() = LoginMobileNumberFragment()
    }
}