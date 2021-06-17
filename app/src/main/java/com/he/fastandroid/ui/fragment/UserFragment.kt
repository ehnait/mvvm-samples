package com.he.fastandroid.ui.fragment

import androidx.lifecycle.lifecycleScope
import coil.load
import com.he.common.base.BaseFragment
import com.he.common.extension.singleClick
import com.he.common.extension.start
import com.he.fastandroid.MyApplication
import com.he.fastandroid.R
import com.he.fastandroid.room.AppDatabase
import com.he.fastandroid.ui.activity.DownloadActivity
import com.he.fastandroid.ui.activity.MainActivity
import com.he.fastandroid.ui.activity.SplashActivity
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.launch

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class UserFragment : BaseFragment<MainActivity>() {

    override fun getlayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun initView() {
        lifecycleScope.launch {
            val dao = AppDatabase.getInstance(requireContext()).userDao()
            val user = dao.findByName(MyApplication.instance.spUserName)
            if (user.userPortrai.isNullOrEmpty().not()) {
                iv_avatar.load(user.userPortrai)
            }
            if (user.userName.isNullOrEmpty().not()) {
                tv_name.text = user.userName
            }
        }
        tv_download.singleClick {
            context?.start<DownloadActivity>()
        }
        btn_logout.singleClick {
            MyApplication.instance.spUserName = ""
            requireActivity().start<SplashActivity>()
            finish()
        }

    }

    override fun initData() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }
}

