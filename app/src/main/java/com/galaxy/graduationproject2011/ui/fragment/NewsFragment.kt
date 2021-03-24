package com.galaxy.graduationproject2011.ui.fragment

import androidx.lifecycle.lifecycleScope
import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.remote.Service
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import com.galaxy.http.requestApi
import kotlinx.coroutines.launch

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class NewsFragment : BaseFragment<MainActivity>() {

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    override fun initView() {
    }

    override fun initData() {
        lifecycleScope.launch {
            requestApi({
                Service.apiServiceV2.getNews()
            }, {
                if (it.isOk()) {

                }
            })
        }
    }

    override fun getlayoutId(): Int {
        return R.layout.fragment_news
    }
}
