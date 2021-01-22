package com.galaxy.http

import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Liam.Zheng on 2021/1/13
 *
 * Des:
 */

fun <Response> CoroutineScope.request(
    callBlock: suspend () -> Response,
    onSuccess: ((Response) -> Unit)? = null,
    onThrowable: ((Throwable) -> Unit)? = null,
    onFinally: (() -> Unit)? = null
) {
    val handler = CoroutineExceptionHandler { _, exception ->
        onThrowable?.invoke(exception)
    }
    launch(context = Dispatchers.Main + handler) {
        try {
            onSuccess?.invoke(withContext(Dispatchers.IO) {
                callBlock.invoke()
            })
        } catch (exception: Throwable) {
            onThrowable?.invoke(exception)
        } finally {
            onFinally?.invoke()
        }
    }
}

inline fun <Service> apiService(
    service: Class<Service>,
    configuration: Configuration.() -> Unit
): Service {
    val finalConfiguration = Configuration().apply(configuration)

    val finalOkHttpBuilder = finalConfiguration.buidOkHttp?.invoke(OkHttpClient.Builder())
        ?: OkHttpClient.Builder()

    val finalRetrofitBuilder = finalConfiguration.buidRetrofit?.invoke(Retrofit.Builder())
        ?: Retrofit.Builder()
            .client(finalOkHttpBuilder.build())

    return finalRetrofitBuilder.build().create(service)
}

class Configuration {
    var buidOkHttp: ((OkHttpClient.Builder) -> OkHttpClient.Builder)? = null
    var buidRetrofit: ((Retrofit.Builder) -> Retrofit.Builder)? = null

    fun okHttp(builder: (OkHttpClient.Builder) -> OkHttpClient.Builder) {
        this.buidOkHttp = builder
    }

    fun retrofit(builder: (Retrofit.Builder) -> Retrofit.Builder) {
        this.buidRetrofit = builder
    }
}