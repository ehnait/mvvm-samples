package com.example.lib_common.base

import androidx.lifecycle.ViewModel
import com.example.lib_common.di.DefaultDispatcher
import com.example.lib_common.di.IoDispatcher
import com.example.lib_common.http.romote.parseApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    @Inject
    @DefaultDispatcher
    lateinit var defaultDispatcher: CoroutineDispatcher

    private val _uiState = MutableStateFlow<UiState>(UiState.Content)
    val uiState = _uiState.asStateFlow()
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiState.asSharedFlow()

    protected suspend fun emitUiState(uiState: UiState) {
        _uiState.emit(uiState)
    }

    protected fun tryEmitUiState(uiState: UiState) {
        _uiState.tryEmit(uiState)
    }

    protected suspend fun emitUiEvent(uiEvent: UiEvent) {
        _uiEvent.emit(uiEvent)
    }

    protected fun tryEmitUiEvent(uiEvent: UiEvent) {
        _uiEvent.tryEmit(uiEvent)
    }


    protected fun <T : Any> Flow<T>.apiAction(): Flow<T> {
        return onStart {
            _uiState.emit(UiState.Loading)
        }.onEmpty {
            //当流完成却没有发出任何元素时回调
            _uiState.emit(UiState.Empty)
        }.onCompletion {
            _uiState.emit(UiState.Content)
        }.catch { it: Throwable ->
            _uiState.emit(UiState.Error)
            it.parseApiException()
        }
    }
}

sealed class UiState {
    object Content : UiState()
    object Loading : UiState()
    object Error : UiState()
    object Empty : UiState()
}

sealed class UiEvent {
    data class Toast(val msg: String, val e: Exception? = null) : UiEvent()
}