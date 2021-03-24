package com.galaxy.graduationproject2011.ui.fragment

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.remote.Service
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import com.galaxy.graduationproject2011.ui.adapter.NewsAdapter
import com.galaxy.http.requestApi
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.launch

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class NewsFragment : BaseFragment<MainActivity>() {
    private var newsAdapter: NewsAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    override fun getlayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun initView() {
        newsAdapter = NewsAdapter()
        news_list.adapter = newsAdapter
        news_list.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initData() {
        lifecycleScope.launch {
            requestApi({
                Service.apiServiceV2.getNews()
            }, {
                if (it.isOk()) {
                    newsAdapter?.setNewInstance(it.data)
                }
            })
        }
    }

}
