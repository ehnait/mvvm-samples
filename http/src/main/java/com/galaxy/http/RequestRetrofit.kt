package com.galaxy.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Liam.Zheng on 2020/8/20
 *
 * Des:
 */
 object RequestRetrofit {

    private fun getOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    private fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
    }

    fun <Service> apiService(
        service: Class<Service>,
        configuration: (Configuration.() -> Unit)? = null
    ): Service {

        val requestRetrofitConfiguration = configuration?.let { Configuration().apply(it) }

        val finalOkHttpBuilder =
            requestRetrofitConfiguration?.buidOkHttp?.invoke(getOkHttpBuilder()) ?: getOkHttpBuilder()

        val finalRetrofitBuilder =
            (requestRetrofitConfiguration?.buidRetrofit?.invoke(getRetrofitBuilder())
                ?: getRetrofitBuilder())
                .client(finalOkHttpBuilder.build())
        return finalRetrofitBuilder.build().create(service)
    }

    class Configuration {
        internal var buidOkHttp: ((OkHttpClient.Builder) -> OkHttpClient.Builder)? = null
        internal var buidRetrofit: ((Retrofit.Builder) -> Retrofit.Builder)? = null

        infix fun okHttp(builder: ((OkHttpClient.Builder) -> OkHttpClient.Builder)?) {
            this.buidOkHttp = builder
        }

        infix fun retrofit(builder: ((Retrofit.Builder) -> Retrofit.Builder)?) {
            this.buidRetrofit = builder
        }
    }
}

