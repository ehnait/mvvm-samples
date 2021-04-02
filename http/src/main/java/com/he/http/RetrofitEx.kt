package com.he.http

import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Liam.Zheng on 2021/1/13
 *
 * Des:
 */

fun <Response> CoroutineScope.requestApi(
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

inline fun <Service> getApiService(
    service: Class<Service>,
    configuration: Configuration.() -> Unit
): Service {
    val retrofitConfiguration = Configuration().apply(configuration)

    val finalOkHttpClient = (retrofitConfiguration.buidOkHttpClient?.invoke(OkHttpClient.Builder())
        ?: OkHttpClient.Builder()).build()

    val finalRetrofit =
        (retrofitConfiguration.buidRetrofit?.invoke(Retrofit.Builder())
            ?: Retrofit.Builder()).client(finalOkHttpClient).build()
    return finalRetrofit.create(service)
}


class Configuration {
    var buidOkHttpClient: ((OkHttpClient.Builder) -> OkHttpClient.Builder)? = null
    var buidRetrofit: ((Retrofit.Builder) -> Retrofit.Builder)? = null

    fun okHttpClientBuilder(builder: (OkHttpClient.Builder) -> OkHttpClient.Builder) {
        this.buidOkHttpClient = builder
    }

    fun retrofitBuilder(builder: (Retrofit.Builder) -> Retrofit.Builder) {
        this.buidRetrofit = builder
    }
}