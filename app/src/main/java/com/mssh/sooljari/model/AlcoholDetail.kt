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
data class AlcoholDetail(
    val title: String? = null,
    val degree: Float? = null,
    val maker: String? = null,
    val category: String? = null,
    val tags: List<String>? = null,
    val explanation: String? = null,
)

@Serializable
data class AlcoholResponseDataObject(
    val id: Long? = null,

    @SerialName("attributes")
    val alcohol: AlcoholDetail? = null
)

@Serializable
data class AlcoholResponse(
    @SerialName("data")
    val dataObject: AlcoholResponseDataObject? = null
)