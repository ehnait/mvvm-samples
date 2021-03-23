package com.galaxy.graduationproject2011.entity

/**
 * Created by Liam.Zheng on 2021/1/22
 *
 * Des:
 */
open class BaseResponse<Data> {
    val code: Int? = null
    val msg: String? = null
    val data: Data? = null
    fun isOk(): Boolean {
        return code == 200
    }
}

data class RandPwdResponse<Data>(val password: String) : BaseResponse<Data>()
data class RandPortraitResponse<Data>(val pic_url: String) : BaseResponse<Data>()

