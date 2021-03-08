package com.galaxy.graduationproject2011.remote

import com.galaxy.graduationproject2011.BuildConfig
import com.galaxy.graduationproject2011.entity.Constant
import com.galaxy.http.HeaderInterceptor
import com.galaxy.http.getApiService
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap
import java.util.concurrent.TimeUnit

object Service {
    private var DEBUG = BuildConfig.DEBUG
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L

    val apiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        getApiService(ApiService::class.java) {
            okHttp {
                it
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
                    )
                    .addInterceptor(HeaderInterceptor(debug = DEBUG).apply {
                        val headersMap = HashMap<String, String>(3)
                        headersMap["Accept"] = "application/json"
                        headersMap["Accept-Encoding"] = "gzip"
                        headersMap["Content-Type"] = "application/json; charset=utf-8"
                        put(headersMap)
                    })
            }
            retrofit {
                it.addConverterFactory(GsonConverterFactory.create())
                it.baseUrl(Constant.API_BASE_URL)
            }
        }
    }

}
