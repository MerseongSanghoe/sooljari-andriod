package com.mssh.sooljari.model

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: Long? = null,
    val url: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val publishedAt: String? = null
)