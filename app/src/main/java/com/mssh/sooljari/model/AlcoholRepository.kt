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
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

val BASE_URL = "http://211.37.148.214/api"
val LOGIN_ID = "testandroid"
val LOGIN_PASSWORD = "catdog09321"

const val BASE_URL_ = "http://211.37.148.214"

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

    data class SearchRequest(
        val reqUrl: String,
        val result: AlcoholResults
    )

    //최초 검색 결과 가져오기
    suspend fun getInitialResults(query: String): SearchRequest {
        val url = queryToUrl(query)
        val result: AlcoholResults = client.get(url).body()

        return SearchRequest(url, result)
    }

    //유저 쿼리를 url로 변환
    private fun queryToUrl(query: String): String {
        val words = query.split(" ")
        val tagList = mutableListOf<String>()
        val keywordList = mutableListOf<String>()


        for (w in words) {
            if (w.startsWith("#")) {
                //해쉬 문자 제외하고 추가
                tagList.add(w.substring(1))
            } else {
                keywordList.add(w)
            }
        }

        val tag = tagList.joinToString(" ")
        val keyword = keywordList.joinToString(" ")

        val parameters = ParametersBuilder()

        if (tag.isNotBlank()) {
            parameters.append("t", URLEncoder.encode(tag, StandardCharsets.UTF_8.toString()))
        }

        if (keyword.isNotBlank()) {
            parameters.append("s", URLEncoder.encode(keyword, StandardCharsets.UTF_8.toString()))
        }

        val url = URLBuilder().apply {
            protocol = URLProtocol.HTTP
            host = "211.37.148.214"
            encodedPath = "/api/alcohol/search"
            encodedParameters = parameters
        }.buildString()

        return url
    }

    //추가 검색 결과 가져오기
    suspend fun getMoreResults(url: String, page: Int): AlcoholResults {
        Log.d("url", "${url}&page=$page")
        val result: AlcoholResults = client.get("${url}&page=$page").body()
        Log.d("getMoreResults", result.toString())

        return result
    }

    //strip용 로그인
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

    //술 상세 정보 가져오기
    suspend fun getAlcoholInfo(id: Long): AlcoholInfo {
        val url = "$BASE_URL/alcohols/$id"

        if (jwt == null) {
            login(LOGIN_ID, LOGIN_PASSWORD)
        }

        val result: AlcoholInfo = client.get(url) {
            header("Authorization", "Bearer $jwt")
        }.body()

        return result
    }

    //태그로 술 가져오기
    suspend fun getAlcoholsByTag(tag: String): SearchedByTagAlcoholResults {
        val url = "$BASE_URL/tag/bytag/"
        val query = URLEncoder.encode(tag, "UTF-8")
        val response = client.get("$url$query")
        if (response.status != HttpStatusCode.OK)
            Log.d("http", response.toString());
        val results: SearchedByTagAlcoholResults = response.body()

        return results
    }

    //자동완성 키워드 가져오기
    suspend fun getAutoCompleteKeywords(query: String): Keywords {
        val url = "$BASE_URL/autoc?k="
        val encodedUrl = URLEncoder.encode(query, "UTF-8")
        val result: Keywords = client.get("$url$encodedUrl").body()

        return result
    }
}