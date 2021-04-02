package com.he.fastandroid.ui.fragment

import android.os.CountDownTimer
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import cn.smssdk.SMSSDK.*
import com.he.common.base.BaseFragment
import com.he.common.extension.*
import com.he.common.utils.RegUtils
import com.he.fastandroid.MyApplication
import com.he.fastandroid.R
import com.he.fastandroid.remote.Service
import com.he.fastandroid.room.AppDatabase
import com.he.fastandroid.room.User
import com.he.fastandroid.ui.activity.LoginActivity
import com.he.fastandroid.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login_mobile_number.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.coroutines.*
import org.json.JSONObject


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginMobileNumberFragment : BaseFragment<LoginActivity>() {
    private lateinit var eventhandler: EventHandler
    private lateinit var countDownTimer: CountDownTimer

    override fun getlayoutId(): Int {
        return R.layout.fragment_login_mobile_number
    }

    override fun initView() {
        tvTitle.text = getString(R.string.log_in)
        initCountDown()
        initSMSSDK()
        ivBack.singleClick {
            findNavController().navigateUp()
        }
        tvSend.singleClick {
            if (!requireActivity().isInternetOn()) {
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
            if (!requireActivity().isInternetOn()) {
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
            if (!requireActivity().isInternetOn()) {
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

    override fun initData() {

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
                            lifecycleScope.launch {
                                val phoneNumber = etPhone.text.toString().trim()
                                if (getRandPwd(phoneNumber)) {
                                    requireActivity().start<MainActivity>()
                                    requireActivity().finish()
                                }
                            }
                        }
                    }
                } else {
                    data?.let { processError(it) }
                }
            }
        }
        registerEventHandler(eventhandler)
    }


    private suspend fun getRandPwd(phoneNumber: String): Boolean {
        var success = false
        withContext(Dispatchers.IO) {
            try {
                withTimeout(4000) {
                    val apiResponse = Service.apiService.getRandPwd()
                    if (200 == apiResponse.code() && null != apiResponse.body()) {
                        AppDatabase.getInstance(requireContext()).userDao().also {
                            val user = User(
                                userName = phoneNumber,
                                userPassword = apiResponse.body()?.password
                            )
                            it.insertAll(user)
                            MyApplication.instance.spUserName=phoneNumber
                        }
                        success = true
                    }

                }
            } catch (sww: Throwable) {
                success = false
            }
        }
        return success
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