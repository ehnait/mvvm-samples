package com.galaxy.graduationproject2011.remote

import android.util.Log.VERBOSE
import com.galaxy.graduationproject2011.BuildConfig
import com.galaxy.http.HeaderInterceptor
import com.galaxy.http.getApiService
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object Service {
    private const val API_BASE_URL = "https://api.66mz8.com/"
    private const val API_BASE_URL_v2 = "https://api.isoyu.com/"
    private var DEBUG = BuildConfig.DEBUG
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L

    val apiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        getApiService(ApiService::class.java) {
            okHttpClientBuilder {
                it
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(
                        (LoggingInterceptor.Builder()
                            .setLevel(Level.BASIC)
                            .log(VERBOSE)
                            .tag("okHttp")
                            .build())
                    )
                    .addInterceptor(HeaderInterceptor(debug = DEBUG).apply {
                        val headersMap = HashMap<String, String>(3)
                        headersMap["Accept"] = "application/json"
                        headersMap["Accept-Encoding"] = "gzip"
                        headersMap["Content-Type"] = "application/json; charset=utf-8"
                        put(headersMap)
                    })
            }
            retrofitBuilder {
                it.addConverterFactory(GsonConverterFactory.create())
                it.baseUrl(API_BASE_URL)
            }
        }
    }
    val apiServiceV2 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        getApiService(ApiService::class.java) {
            okHttpClientBuilder {
                it
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(
                        LoggingInterceptor.Builder()
                            .setLevel(Level.BASIC)
                            .log(VERBOSE)
                            .tag("okHttp")
                            .build()
                    )
                    .addInterceptor(HeaderInterceptor(debug = DEBUG).apply {
                        val headersMap = HashMap<String, String>(3)
                        headersMap["Accept"] = "application/json"
                        headersMap["Accept-Encoding"] = "gzip"
                        headersMap["Content-Type"] = "application/json; charset=utf-8"
                        put(headersMap)
                    })
            }
            retrofitBuilder {
                it.addConverterFactory(GsonConverterFactory.create())
                it.baseUrl(API_BASE_URL_v2)
            }
        }
    }

}
