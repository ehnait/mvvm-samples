package com.example.lib_common.http.romote

import androidx.annotation.Keep
import com.example.lib_common.R
import com.example.lib_common.ext.application
import com.google.gson.JsonIOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

@Keep
data class BaseResponse<out T>(val code: Int, val message: String, val data: T)

@Keep
data class BizException(val code: Int, val message: String)

data class CatchException(private val t:Throwable ) {
    fun parse(): String? {
        return when (t) {
            is SocketTimeoutException -> {//网络超时
                application.getString(R.string.socket_timeout_exception)
            }
            is ConnectException -> { //均视为网络错误
                application.getString(R.string.connect_exception)
            }
            is JSONException, is ParseException, is JsonIOException -> {   //解析错误
                application.getString(R.string.json_exception)
            }
            is UnknownHostException -> {
                application.getString(R.string.unknown_host_exception)
            }
            is IllegalArgumentException -> {
                application.getString(R.string.illegal_argument_exception)
            }

            is HttpException -> {
                application.getString(R.string.http_exception,"${t.code()}")
            }

            is kotlinx.coroutines.CancellationException -> {
                //Ignore
                null
            }
            is RuntimeException -> {//服务器返回的错误信息
                t.message.toString()
            }
            else -> {
                application.getString(R.string.unknown_exception)
            }
        }
    }
}


inline val BaseResponse<*>.isSuccess: Boolean
    get() {
        return code == 200
    }

suspend fun <T : Any> BaseResponse<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): BaseResponse<T> {
    return coroutineScope {
        if (isSuccess) successBlock?.invoke(this, this@doSuccess.data)
        this@doSuccess
    }

}

suspend fun <T : Any> BaseResponse<T>.doError(errorBlock: (suspend CoroutineScope.(BizException) -> Unit)? = null): BaseResponse<T> {
    return coroutineScope {
        if (!isSuccess) errorBlock?.invoke(
            this,
            BizException(this@doError.code, this@doError.message)
        )
        this@doError
    }
}

suspend fun <T : Any> BaseResponse<T>.toResult(
    successStart: (suspend CoroutineScope.() -> Unit)? = null,
    errorStart: (suspend CoroutineScope.() -> Unit)? = null
): Result<T> {
    return coroutineScope {
        if (isSuccess) {
            successStart?.invoke(this)
            Result.Success(data)
        } else {
            errorStart?.invoke(this)
            Result.Error(BizException(code, message))
        }
    }
}
