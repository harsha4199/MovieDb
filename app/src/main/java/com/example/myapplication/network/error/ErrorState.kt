package com.example.myapplication.network.error

sealed class ErrorState(val message: String?) {
    class NetworkError(message: String?) : ErrorState(message)
    class ServerError(message: String?) : ErrorState(message)
    class UnexpectedError(message: String?) : ErrorState(message)
}

