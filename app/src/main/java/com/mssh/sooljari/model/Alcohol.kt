package com.mssh.sooljari.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val count: Int? = null
)

