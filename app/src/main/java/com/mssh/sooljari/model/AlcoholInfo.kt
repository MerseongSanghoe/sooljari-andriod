package com.mssh.sooljari.model

import android.util.Log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LogInRequest(
    val identifier: String,
    val password: String
)

@Serializable
data class JwtToken(
    val jwt: String
)

@Serializable
data class AlcoholInfo(
    val id: Long? = null,
    val title: String? = null,
    val degree: Float? = null,
    val category: String? = null,
    val maker: Maker? = null,
    val explanation: String? = null,
    @SerialName("images")
    val imageList: List<Image>? = emptyList(),
    @SerialName("tags")
    val tagList: List<Tag>? = emptyList(),

    val createdAt: String? = null,
    val updatedAt: String? = null,
    val publishedAt: String? = null,

    val link: String? = null,
    @SerialName("store_link")
    val storeLink: String? = null,
    @SerialName("sub_link")
    val subLink: String? = null,
)

@Serializable
data class Maker(
    val id: Long? = null,
    val name: String? = null,
    val nation: String? = null,
    val location: String? = null,
    val url: String? = null,

    val createdAt: String? = null,
    val updatedAt: String? = null,
    val publishedAt: String? = null,
)

@Serializable
data class Tag(
    @SerialName("title")
    val string: String? = null,
    val weight: Int? = null
)

fun tagListToStringList(tagList: List<Tag>): List<String> {
    val stringList: MutableList<String> = mutableListOf()

    tagList.forEach { tag ->
        if (tag.string == null) return@forEach

        stringList.add(tag.string)
    }

    return stringList
}

fun addHash(tagList: List<String>): List<String> {
    val hashAddedList: MutableList<String> = mutableListOf()

    tagList.forEach { tag ->
        hashAddedList += "#$tag"
    }

    return hashAddedList
}