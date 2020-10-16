package com.galaxy.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Liam.Zheng on 2020/8/24
 *
 * Des: 公共头拦截器
 */
class HeaderInterceptor constructor(debug: Boolean) : Interceptor {
    private var mDebug = debug
    private var headers = hashMapOf<String, String>()

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        headers.forEach { (k, v) ->
            requestBuilder.addHeader(k, v)
        }
        if (mDebug) {
            Log.d(TAG, "intercept: $headers")
        }

        return chain.proceed(requestBuilder.build())
    }

    fun put(key: String, value: String): HeaderInterceptor {
        headers[key] = value
        return this
    }

    fun put(headers: HashMap<String, String>): HeaderInterceptor {
        this.headers.putAll(headers)
        return this
    }

    fun clear(): HeaderInterceptor {
        this.headers.clear()
        return this
    }

    companion object {
        private const val TAG = "HeadersInterceptor"
    }
}
