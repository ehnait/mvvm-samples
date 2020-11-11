package com.galaxy.graduationproject2011.ui.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.galaxy.graduationproject2011.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class MainActivity : AppBaseActivity(R.layout.activity_main) {
    override fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(navigation, navController)
    }

}