package com.galaxy.graduationproject2011.ui.fragment

import android.content.Intent
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.start
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.remote.Service
import com.galaxy.graduationproject2011.ui.activity.BrowserActivity
import com.galaxy.graduationproject2011.ui.activity.BrowserActivity.Companion.BROWSER_URL
import com.galaxy.graduationproject2011.ui.activity.LoginActivity
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
        newsAdapter?.setOnItemClickListener { baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int ->
            requireActivity().start<BrowserActivity>(Intent().apply {
                putExtra(BROWSER_URL, newsAdapter?.data?.get(i)?.url)
            })
        }
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
