package com.galaxy.graduationproject2011.remote

import com.galaxy.graduationproject2011.entity.RandPortraitResponse
import com.galaxy.graduationproject2011.entity.RandPwdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/rand.pwd.php")
    suspend fun getRandPwd(@Query("a") a: String = "6"): Response<RandPwdResponse<Nothing>>

    @GET("api/rand.portrait.php")
    suspend fun getRandPortrait(
        @Query("type") type: String = "动漫",
        @Query("format") format: String = "json", ): RandPortraitResponse<Nothing>
}
