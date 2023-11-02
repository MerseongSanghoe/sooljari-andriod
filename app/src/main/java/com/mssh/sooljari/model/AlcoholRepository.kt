package com.mssh.sooljari.model

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.URLEncoder

val BASE_URL = "http://211.37.148.214/api"
class AlcoholRepository {
    private var jwt: String? = null

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

    suspend fun getInitialResults(keyword: String): AlcoholResults {
        val url = "$BASE_URL/alcohol/search?s="
        val query = URLEncoder.encode(keyword, "UTF-8")
        val results: AlcoholResults = client.get("$url$query").body()

        return results
    }

    suspend fun getMoreResults(keyword: String, page: Int): AlcoholResults {
        val url = "$BASE_URL/alcohol/search?s="
        val query = URLEncoder.encode(keyword, "UTF-8")
        val results: AlcoholResults = client.get("$url$query&page=$page").body()

        return results
    }

    suspend fun requestResults(keyword: String, page: Int = 0): AlcoholResults {
        val url = "$BASE_URL/alcohol/search?s="
        val query = URLEncoder.encode(keyword, "UTF-8")
        val results: AlcoholResults = client.get("$url$query&page=$page").body()

        Log.d("results", "query:$keyword  $results")

        return results
    }

    suspend fun login(id: String, pw: String) {
        val response: HttpResponse = client.post("$BASE_URL/auth/local") {
            contentType(ContentType.Application.Json)
            setBody(LogInRequest(id, pw))
        }

        if (response.status != HttpStatusCode.OK) {
            Log.e("로그인 실패", response.status.toString())
        } else {
            val jwtToken: JwtToken = response.body()
            jwt = jwtToken.jwt
            Log.d("로그인 성공", jwt.toString())
        }
    }

    suspend fun getAlcoholDetail(id: Long): AlcoholResponse {
        val url = "$BASE_URL/alcohols/$id"

        if (jwt == null) {
            login("testandroid", "catdog09321")
        }

        val result: AlcoholResponse = client.get(url) {
            header("Authorization", "Bearer $jwt")
        }.body()

        return result
    }
}