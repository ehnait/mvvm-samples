package com.galaxy.graduationproject2011.remote

import com.galaxy.graduationproject2011.entity.BaseResponse
import com.galaxy.graduationproject2011.entity.RandPortraitResponse
import com.galaxy.graduationproject2011.entity.RandPwdResponse
import retrofit2.http.*

interface ApiService {
    @GET("api/rand.pwd.php")
    suspend fun getRandPwd(@Query("a") a: String): BaseResponse<RandPwdResponse>

    @GET("api/rand.portrait.php")
    suspend fun getRandPortrait(
        @Query("type") type: String,
        @Query("format") format: String = "json", ):BaseResponse<RandPortraitResponse>
}
