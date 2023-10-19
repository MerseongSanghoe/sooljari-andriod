package com.mssh.sooljari.model

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.URLEncoder


class AlcoholRepository {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }
    }

    suspend fun requestResults(keyword: String): AlcoholResults {
        val url = "http://211.37.148.214/api/alcohol/search?s="
        val query = URLEncoder.encode(keyword, "UTF-8")
        val results: AlcoholResults = client.get("$url$query").body()

        Log.d("results", "query:$keyword  $results")

        return results
    }
}