package com.galaxy.graduationproject2011.remote

import com.galaxy.graduationproject2011.BuildConfig
import com.galaxy.graduationproject2011.entity.Constant
import com.galaxy.http.apiService
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object Service {
    private var DEBUG = BuildConfig.DEBUG
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L

    val apiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
       apiService(ApiService::class.java) {
            okHttp {
                it
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                        .addNetworkInterceptor(
                                HttpLoggingInterceptor()
                                        .setLevel(if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
                        )
            }
            retrofit {
                it.baseUrl(Constant.API_BASE_URL)
            }
        }
    }

}
