package com.galaxy.graduationproject2011.ui.fragment

import androidx.lifecycle.lifecycleScope
import coil.load
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.singleClick
import com.galaxy.common.extension.start
import com.galaxy.graduationproject2011.MyApplication
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.remote.Service
import com.galaxy.graduationproject2011.room.AppDatabase
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import com.galaxy.graduationproject2011.ui.activity.SplashActivity
import com.galaxy.http.requestApi
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.Dispatchers
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

