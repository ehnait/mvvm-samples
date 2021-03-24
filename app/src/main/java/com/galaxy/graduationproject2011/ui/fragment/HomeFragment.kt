package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.loge
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.remote.Service
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import com.galaxy.http.requestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class HomeFragment : BaseFragment<MainActivity>() {



    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun getlayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun initView() {

    }

    override fun initData() {
        lifecycleScope.launch {
            requestApi({
                Service.apiServiceV2.getVideo()
            }, {
                if (it.isOk()) {

                }
            })
        }
    }
}
