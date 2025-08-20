package org.example.pokedex.common

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import org.example.pokedex.data.remote.error.PokeDexException
import org.example.pokedex.data.remote.error.mapExceptionToNetworkError

suspend inline fun <reified T> safeApiCall(
    crossinline response: suspend () -> HttpResponse
): T = withContext(Dispatchers.IO) {

    return@withContext try {
        response().body()
    } catch (exception: IOException) {
       throw PokeDexException(mapExceptionToNetworkError(exception))
    }
}