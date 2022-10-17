package com.example.lib_common.ext

inline fun <T> unsafeLazy(crossinline initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) {
        initializer()
    }