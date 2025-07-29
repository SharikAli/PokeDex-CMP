package org.example.pokedex.data.remote

import io.ktor.client.HttpClient

expect fun createPlatformHttpClient(): HttpClient