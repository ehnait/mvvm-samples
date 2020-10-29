package com.galaxy.graduationproject2011.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.galaxy.common.base.BaseActivity
import com.galaxy.graduationproject2011.R

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun initView() {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        currentFocus?.let {
            val mInputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            return mInputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
        return super.onTouchEvent(event)
    }
     fun isNetworkConnected(): Boolean {
        val manager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}