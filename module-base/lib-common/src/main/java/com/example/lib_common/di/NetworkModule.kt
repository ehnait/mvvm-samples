package com.example.lib_common.di

import com.example.lib_common.http.interceptor.AuthInterceptor
import com.example.lib_common.http.interceptor.OtherInterceptor
import com.example.lib_common.http.interceptor.logInterceptor
import com.example.lib_common.http.romote.BaseService
import com.example.lib_common.http.romote.CommonService
import com.example.lib_common.http.romote.OtherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    /**
     * @Provides 常用于被 @Module 注解标记类的内部的方法，并提供依赖项对象。
     * @Singleton 提供单例
     */
    @Provides
    @Singleton
    @AuthInterceptorOkHttpClient
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @OtherInterceptorOkHttpClient
    fun provideOtherInterceptorOkHttpClient(
        otherInterceptor: OtherInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(otherInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideCommonService( @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient): CommonService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BaseService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommonService::class.java)
    }

    @Provides
    @Singleton
    fun provideOtherService( @OtherInterceptorOkHttpClient okHttpClient: OkHttpClient): OtherService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BaseService.OTHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OtherService::class.java)
    }

}
