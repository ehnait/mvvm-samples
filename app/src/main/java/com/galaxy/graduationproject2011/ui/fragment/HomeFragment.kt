package com.galaxy.graduationproject2011.ui.fragment

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galaxy.common.base.BaseFragment
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.remote.Service
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import com.galaxy.graduationproject2011.ui.adapter.VideoAdapter
import com.galaxy.http.requestApi
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class HomeFragment : BaseFragment<MainActivity>() {

    private var listNormalAdapter: VideoAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun getlayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun initView() {
        listNormalAdapter = VideoAdapter()
        video_list.adapter = listNormalAdapter
        video_list.layoutManager = LinearLayoutManager(requireContext())
        video_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //大于0说明有播放
                if (GSYVideoManager.instance().playPosition >= 0) {
                    //当前播放的位置
                    val position = GSYVideoManager.instance().playPosition
                    //对应的播放列表TAG
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                        if (GSYVideoManager.instance().playTag == VideoAdapter.TAG && (position < firstVisibleItem || position > lastVisibleItem)) {
                            if (GSYVideoManager.isFullState(requireActivity())) {
                                return
                            }
                            //如果滑出去了上面和下面就是否，和今日头条一样
                            GSYVideoManager.releaseAllVideos()
                            listNormalAdapter!!.notifyDataSetChanged()
                        }
                    }
                }
            }
        })

    }

    override fun initData() {
        lifecycleScope.launch {
            requestApi({
                Service.apiServiceV2.getVideo()
            }, {
                if (it.isOk()) {
                    listNormalAdapter?.setNewInstance(it.data)
                }
            })
        }
    }

}
