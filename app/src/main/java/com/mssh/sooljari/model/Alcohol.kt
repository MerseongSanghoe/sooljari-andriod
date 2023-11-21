package com.mssh.sooljari.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
검색 결과로 반환되는 데이터
 */
@Serializable
data class Alcohol(
    val id: Long? = null,

    @SerialName("title")
    val name: String? = null,

    val category: String? = null,
    val degree: Float? = null,
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
data class SearchedByTagAlcohol(
    @SerialName("alcohol")
    val name: String? = null,

    val weight: Int? = null,

    @SerialName("otherTags")
    val relatedTags: List<Tag> = emptyList()
)

@Serializable
data class SearchedByTagAlcoholResults(
    val data: List<SearchedByTagAlcohol> = emptyList(),
    val count: Int? = null
)

