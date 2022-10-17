package com.example.lib_common.http.interceptor

import com.example.lib_common.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

val logInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
    }
}