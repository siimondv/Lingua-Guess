package com.example.linguaguess.utils


sealed class NetworkResult<T>(
    var data: T? = null,
    var message: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data = data)

    class Error<T>(message: String) : NetworkResult<T>(message = message)
}