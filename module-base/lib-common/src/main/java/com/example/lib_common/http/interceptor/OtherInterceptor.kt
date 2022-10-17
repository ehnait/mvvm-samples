package com.example.lib_common.http.interceptor

import android.content.Context
import android.webkit.WebSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class OtherInterceptor @Inject constructor(@ApplicationContext val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                    removeHeader("User-Agent").also {
                        addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
                    }
            }.build()
        )
    }
}