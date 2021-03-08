package com.galaxy.common.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.galaxy.common.extension.singleClick

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val layoutId: Int
    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(savedInstanceState)
    }

    protected fun initActivity(savedInstanceState: Bundle?) {
        initLayout()
        initView(savedInstanceState)
        initData()
    }

    private fun initLayout() {
        if (layoutId > 0) {
            setContentView(layoutId)
            initSoftKeyboard()
        }

    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        hideSoftKeyboard()// 查看源码得知 startActivity 最终也会调用 startActivityForResult
        super.startActivityForResult(intent, requestCode, options)
    }

    /**
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent)
    }

    override fun finish() {
        hideSoftKeyboard()
        super.finish()
    }

    /**
     * @return ViewGroup 根视图
     */
    protected fun getContentView(): ViewGroup {
        return findViewById(Window.ID_ANDROID_CONTENT)
    }

    /**
     * 初始化软键盘,点击外部隐藏软键盘,提升用户体验
     */
    protected fun initSoftKeyboard() {
        getContentView().singleClick {
            hideSoftKeyboard()
        }
    }

    /**
     * 隐藏软键盘，避免软键盘引发的内存泄露
     */
    private fun hideSoftKeyboard() {
        currentFocus?.let { view: View ->
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let { manager: InputMethodManager ->
                if (manager.isActive(view)) {
                    manager.hideSoftInputFromWindow(
                        view.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        }
    }
}