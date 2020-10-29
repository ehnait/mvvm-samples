package com.galaxy.graduationproject2011.ui.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.singleClick
import com.galaxy.graduationproject2011.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun initView(view: View) {
        text.singleClick {
//            findNavController().navigate(R.id.action_pop_out_of_home)
//
//            val jsonObject = JSONObject("{\n" +
//                    "    \"eventName\": \"BeJson\",\n" +
//                    "    \"source\": \"android\",\n" +
//                    "    \"page\": 88,\n" +
//                    "    \"isNonProfit\": true\n" +
//                    "}")
//            println(jsonObject.toString())
//
//            val map = HashMap<String, Any>()
//            jsonObject.keys().forEach {
//                map[it] = jsonObject[it]
//            }
//            println(map.toString())
//            println(map["event"])
//            println(map.containsKey("eventName"))
//            println(map.getOrDefault("event","custom_event"))
//            println(map.getOrDefault("eventName","custom_event"))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
