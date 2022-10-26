package com.example.lib_common.http.romote

import androidx.annotation.Keep
import com.example.lib_common.R
import com.example.lib_common.ext.application
import com.example.lib_common.ext.toast
import com.google.gson.JsonIOException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

@Keep
data class NetworkResponse<out T>(val code: Int, val message: String, val data: T)

fun Throwable.parseApiException() =
    when (this) {
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
            application.getString(R.string.http_exception, "${this.code()}")
        }

        is kotlinx.coroutines.CancellationException -> {
            //Ignore
            null
        }
        is RuntimeException -> {//服务器返回的错误信息
            this.message.toString()
        }
        else -> {
            application.getString(R.string.unknown_exception)
        }
    }?.also {
        application.toast(it)
    }


inline val NetworkResponse<*>.isSuccess: Boolean
    get() {
        return code == 200
    }

inline fun <T : Any> NetworkResponse<T>.whenSuccess(block: (T) -> Unit) {
    if (isSuccess) {
        block.invoke(data)
    }
}

inline fun <T : Any> NetworkResponse<T>.getOrElse(default: (NetworkResponse<T>) -> T): T =
    if (isSuccess) {
        data
    } else {
        default(this)
    }

fun <T : Any> NetworkResponse<T>.getOrNull(): T? =
    if (isSuccess) {
        data
    } else {
        null
    }

//suspend fun <T : Any> BaseResponse<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): BaseResponse<T> {
//    return coroutineScope {
//        if (isSuccess) successBlock?.invoke(this, this@doSuccess.data)
//        this@doSuccess
//    }
//
//}
//
//suspend fun <T : Any> BaseResponse<T>.doError(errorBlock: (suspend CoroutineScope.(BizException) -> Unit)? = null): BaseResponse<T> {
//    return coroutineScope {
//        if (!isSuccess) errorBlock?.invoke(
//            this,
//            BizException(this@doError.code, this@doError.message)
//        )
//        this@doError
//    }
//}
//
//suspend fun <T : Any> BaseResponse<T>.toResult(
//    successStart: (suspend CoroutineScope.() -> Unit)? = null,
//    errorStart: (suspend CoroutineScope.() -> Unit)? = null
//): Result<T> {
//    return coroutineScope {
//        if (isSuccess) {
//            successStart?.invoke(this)
//            Result.Success(data)
//        } else {
//            errorStart?.invoke(this)
//            Result.Failure(BizException(code, message))
//        }
//    }
//}
