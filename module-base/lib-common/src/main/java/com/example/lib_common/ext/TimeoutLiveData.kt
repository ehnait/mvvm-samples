package com.example.lib_common.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Creates a copy of this [LiveData], but its value is automatically set to the [defaultValue]
 * in [timeoutMillis] milliseconds after every value change.
 */
fun <T> LiveData<T>.timeout(
    scope: CoroutineScope,
    timeoutMillis: Long,
    defaultValue: T
): LiveData<T> {
    return TimeoutLiveData(this, scope, timeoutMillis, defaultValue)
}

private class TimeoutLiveData<T>(
    source: LiveData<T>,
    scope: CoroutineScope,
    timeoutMillis: Long,
    defaultValue: T
) : MediatorLiveData<T>() {

    init {
        var previousJob: Job? = null
        addSource(source) { value ->
            previousJob = scope.launch {
                // Cancel it if there's already a timeout set up.
                previousJob?.cancel()
                previousJob = null
                // Set the source value, wait, and set the default value.
                this@TimeoutLiveData.value = value
                delay(timeoutMillis)
                this@TimeoutLiveData.value = defaultValue
            }
        }
    }
}
