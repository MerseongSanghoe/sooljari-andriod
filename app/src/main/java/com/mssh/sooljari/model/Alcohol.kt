package com.mssh.sooljari.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
검색 결과, 태그 검색 결과로 반환되는 데이터
 */
@Serializable
data class Alcohol(
    val id: Long? = null,
    val score: Long = 0,

    @SerialName("title")
    val name: String? = null,

    val category: String? = null,
    val degree: Float? = null,

    @SerialName("image")
    val imageUrl: String? = null,

    val tags: List<String> = emptyList()
)

@Serializable
data class AlcoholResults(
    val data: List<Alcohol> = emptyList(),
    val count: Int? = null,
    val size: Int? = null,
    val page: Int? = null
)

/*
태그 검색 결과로 반환되는 데이터
 */
@Serializable
@Deprecated("Use Alcohol Instead")
data class SearchedByTagAlcohol(
    val id: Long? = null,
    val weight: Int? = null,

    @SerialName("title")
    val name: String? = null,

    val degree: Float? = null,
    val category: String? = null,

    @SerialName("otherTags")
    val relatedTags: List<Tag> = emptyList()
)

@Serializable
data class SearchedByTagAlcoholResults(
    val data: List<Alcohol> = emptyList(),
    val count: Int? = null
)

val testSearchedByTagAlcoholList: List<Alcohol> =
    listOf(
        Alcohol(
            id = 1,
            score = 1,
            name = "술이름",
            degree = 15.0f,
            category = "소주",
            tags = testTagTitleOnlyList
        ),
        Alcohol(
            id = 1,
            score = 1,
            name = "술이름",
            degree = 15.0f,
            category = "소주",
            tags = testTagTitleOnlyList
        ),
        Alcohol(
            id = 1,
            score = 1,
            name = "술이름",
            degree = 15.0f,
            category = "소주",
            tags = testTagTitleOnlyList
        ),
        Alcohol(
            id = 1,
            score = 1,
            name = "술이름",
            degree = 15.0f,
            category = "소주",
            tags = testTagTitleOnlyList
        ),
        Alcohol(
            id = 1,
            score = 1,
            name = "술이름",
            degree = 15.0f,
            category = "소주",
            tags = testTagTitleOnlyList
        )
    )

