package com.galaxy.http

import kotlinx.coroutines.*

/**
 * Created by Liam.Zheng on 2020/9/21
 *
 * Des:
 */
object Request : CoroutineScope by MainScope() {

    fun <Response> call(callback: RequestCallback<Response>.() -> Unit) {
        RequestCallback<Response>().apply(callback).launch(this)
    }

    fun <Response> call(request: suspend () -> Response,
        onResponse: ((Response) -> Unit),
        onStart: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onFinally: (() -> Unit)? = null
    ) {
        RequestCallback<Response>().apply {
            onRequest {
                request.invoke()
            }
            onResponse {
                onResponse.invoke(it)
            }
            onStart {
                onStart?.invoke()
            }
            onError {
                onError?.invoke(it)
            }
            onFinally {
                onFinally?.invoke()
            }
        }.launch(this)
    }
}

class RequestCallback<Response> {
    private lateinit var request: suspend () -> Response
    private var onStart: (() -> Unit)? = null
    private var onResponse: ((Response) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null
    private var onFinally: (() -> Unit)? = null

    fun onRequest(request: suspend () -> Response) {
        this.request = request
    }

    fun onStart(onStart: (() -> Unit)) {
        this.onStart = onStart
    }

    fun onResponse(onResponse: ((Response) -> Unit)) {
        this.onResponse = onResponse
    }

    fun onError(onError: ((Throwable) -> Unit)) {
        this.onError = onError
    }

    fun onFinally(onFinally: (() -> Unit)) {
        this.onFinally = onFinally
    }

    internal fun launch(viewModelScope: CoroutineScope) {
        viewModelScope.launch(context = Dispatchers.Main) {
            onStart?.invoke()
            try {
                val response = withContext(Dispatchers.IO) {
                    request()
                }
                onResponse?.invoke(response)
            } catch (e: Exception) {
                e.printStackTrace()
                onError?.invoke(e)
            } finally {
                onFinally?.invoke()
            }
        }
    }
}