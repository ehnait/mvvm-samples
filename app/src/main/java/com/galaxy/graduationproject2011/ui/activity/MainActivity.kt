package com.galaxy.graduationproject2011.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.galaxy.common.base.BaseActivity
import com.galaxy.common.extension.start
import com.galaxy.graduationproject2011.MyApplication
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.remote.Service
import com.galaxy.graduationproject2011.room.AppDatabase
import com.galaxy.graduationproject2011.room.User
import com.galaxy.http.requestApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login_mobile_number.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class MainActivity : BaseActivity() {

    override fun getlayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

    }

    override fun initData() {
        lifecycleScope.launch {
            val dao = AppDatabase.getInstance(this@MainActivity).userDao()
            val user = dao.findByName(MyApplication.instance.spUserName)
            if (user.userPortrai.isNullOrEmpty()) {
                requestApi({
                    Service.apiService.getRandPortrait()
                }, {
                    if (it.isOk()) {
                        user.userPortrai = it.pic_url
                        launch(Dispatchers.IO) {
                            dao.update(user)
                        }
                    }
                })
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        navigation.itemIconTintList = null
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(navigation, navController)
    }

}
