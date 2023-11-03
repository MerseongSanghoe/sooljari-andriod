package com.mssh.sooljari.model

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
    val imageList: List<Image>? = null,

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