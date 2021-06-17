package com.he.http

import android.util.Log.VERBOSE
import com.he.http.BuildConfig
import com.he.http.createApiService
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Streaming
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

internal object Service {
    private const val FAKE_BASE_URL = "http://www.example.com"
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    private var DEBUG = BuildConfig.DEBUG


    val downloadApiServsice by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        createApiService(DownloadApiService::class.java) {
            okHttpClientBuilder {
                it
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(
                                LoggingInterceptor.Builder()
                                        .setLevel(if (DEBUG) Level.BASIC else Level.NONE)
                                        .log(VERBOSE)
                                        .tag("okHttp")
                                        .build()
                        )
            }
            retrofitBuilder {
                it.addConverterFactory(GsonConverterFactory.create())
                it.baseUrl(FAKE_BASE_URL)
            }
        }
    }


}

interface DownloadApiService {
    @GET
    @Streaming
    suspend fun getStreaming(@Url url: String, @HeaderMap headers: Map<String, String>): Response<ResponseBody>
}
