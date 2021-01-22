package com.galaxy.graduationproject2011.entity

/**
 * Created by Liam.Zheng on 2021/1/22
 *
 * Des:
 */
class BaseResponse<Data> {
    val code: Int = -1
    val msg: String = ""
    var data: Data? = null
}

data class RandPwdResponse(val password: String)
data class RandPortraitResponse(val imgurl: String)


