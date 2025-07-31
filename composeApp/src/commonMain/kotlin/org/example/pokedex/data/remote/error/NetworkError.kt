package org.example.pokedex.data.remote.error

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException

enum class NetworkError(val code: Int, val message: String) {
    ServerError(500, "Server Error"),
    ClientError(400, "Client Error"),
    TimeoutError(-1, "Request Timeout Error"),
    IOError(-2, "Network Error"),
    UnknownError(-3, "Unexpected Error")
}

inline fun mapExceptionToNetworkError(e: Exception): NetworkError = when (e) {
    is ClientRequestException -> NetworkError.ClientError
    is ServerResponseException -> NetworkError.ServerError
    is HttpRequestTimeoutException -> NetworkError.TimeoutError
    is IOException -> NetworkError.IOError
    else -> NetworkError.UnknownError
}

class PokeDexException(error: NetworkError) : Exception(error.message)