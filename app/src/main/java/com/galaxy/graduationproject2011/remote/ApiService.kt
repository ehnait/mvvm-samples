package com.galaxy.graduationproject2011.remote

import com.galaxy.graduationproject2011.entity.*
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

    @GET("api/Video/video_type")
    suspend fun getVideo(
        @Query("type") type: String = "0",
        @Query("page") page: String = "20", ): BaseResponse<VideoList>

    @GET("api/News/new_list")
    suspend fun getNews(
        @Query("type") type: String = "0",
        @Query("page") page: String = "20", ): BaseResponse<NewsList>
}
