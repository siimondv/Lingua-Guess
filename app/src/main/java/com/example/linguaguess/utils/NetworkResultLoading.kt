package com.example.linguaguess.utils

sealed class NetworkResultLoading<T>(
    var data: T? = null,
    var message: String? = null
) {

    class Success<T>(data: T) : NetworkResultLoading<T>(data = data)

    class Error<T>(message: String = "", data: T? = null) : NetworkResultLoading<T>(data = data, message = message)

    class Loading<T> : NetworkResultLoading<T>()
}