package com.he.fastandroid.ui.activity

import android.os.Bundle
import com.he.common.base.BaseActivity
import com.he.fastandroid.R
import kotlinx.android.synthetic.main.activity_browser.*

class BrowserActivity : BaseActivity() {
    companion object {
        const val BROWSER_URL = "browser_url"
    }

    override fun getlayoutId(): Int {
        return R.layout.activity_browser
    }

    override fun initView(savedInstanceState: Bundle?) {
        val url = intent.getStringExtra(BROWSER_URL)
        webview.loadUrl(url.toString())
    }

    override fun initData() {
    }
}