package com.galaxy.common.base

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by Liam.Zheng on 2020/10/19
 *
 * Des:
 */

abstract class BaseFragment<A : BaseActivity> : Fragment() {
    protected abstract val layoutId: Int
    protected abstract fun initView()
    protected abstract fun initData()

    private var mActivity: A? = null
    private var mRootView: View? = null
    private var isLoading = false //Fragment 是否已经加载过

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 获得全局的 Activity
        mActivity = requireActivity() as A
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isLoading = false
        return if (layoutId > 0) {
            inflater.inflate(layoutId, null).also { mRootView = it }
        } else {
            null
        }
    }

    override fun onDestroyView() {
        isLoading = false
        mRootView = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        if (!isLoading) {
            isLoading = true
            initFragment()
        }
    }

    override fun getView(): View? {
        return mRootView
    }

    protected fun initFragment() {
        initView()
        initData()
    }

    /**
     * 销毁当前 Fragment 所在的 Activity
     */
    fun finish() {
        if (mActivity?.isFinishing == false) {
            mActivity?.finish()
        }
    }


    /**
     *e.g. in Activity
     *   override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
     *      // 回调当前 Fragment 的 onKeyDown 方法
     *      return if (mBaseFragment.onKeyDown(keyCode, event)) {
     *          //do sth
     *           true
     *      } else super.onKeyDown(keyCode, event)
     *    }
     */
    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 默认不拦截按键事件，回传给 Activity
        return false
    }

}
