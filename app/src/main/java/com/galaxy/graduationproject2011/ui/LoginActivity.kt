package com.galaxy.graduationproject2011.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galaxy.common.extensions.singleClick
import com.galaxy.graduationproject2011.R
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mobileLogin.singleClick {
            Timber.d("mobileLogin")
        }
    }
}