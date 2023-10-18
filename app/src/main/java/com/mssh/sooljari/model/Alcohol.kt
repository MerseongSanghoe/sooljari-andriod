package com.mssh.sooljari.model

import kotlinx.serialization.Serializable

@Serializable
data class Alcohol(
    val id: Long,
    val name: String,
    val category: String,
    val degree: Float,
    val tags: List<String> = emptyList()
)

@Serializable
data class AlcoholResults(
    val data: List<Alcohol> = emptyList(),
    val count: Int
)

