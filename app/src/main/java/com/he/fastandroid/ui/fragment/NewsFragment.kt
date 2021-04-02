package com.he.fastandroid.ui.fragment

import android.content.Intent
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.he.common.base.BaseFragment
import com.he.common.extension.start
import com.he.fastandroid.R
import com.he.fastandroid.remote.Service
import com.he.fastandroid.ui.activity.BrowserActivity
import com.he.fastandroid.ui.activity.BrowserActivity.Companion.BROWSER_URL
import com.he.fastandroid.ui.activity.MainActivity
import com.he.fastandroid.ui.adapter.NewsAdapter
import com.he.http.requestApi
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
