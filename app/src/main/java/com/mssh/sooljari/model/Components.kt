package com.mssh.sooljari.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: Long? = null,
    val url: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val publishedAt: String? = null
)

val testImageList: List<Image> = listOf(
    Image(
        url = "https://www.siwse.com/images/contents/landing_visual.png"
    ),
    Image(
        url = "https://bsiwse.com/data/designImages/POPUP_1701598081.jpg"
    ),
    Image(
        url = "https://thesool.com/images/front/cont/info-logo.png"
    ),
    Image(
        url = "https://thesool.com/ckeditor/imageUpload/%EC%8B%9C%EC%9D%8C%EC%83%81%EB%8B%B4%ED%9A%8C1_1692681920574.jpg"
    ),
)

@Serializable
data class Keywords(
    val data: List<String>? = emptyList()
)

@Serializable
data class Tag(
    @SerialName("title")
    val string: String? = null,
    val weight: Int? = null
)

val testTagList: List<Tag> = listOf(
    Tag(string = "test1", weight = 1),
    Tag(string = "test2", weight = 2),
    Tag(string = "test3", weight = 3),
    Tag(string = "test4", weight = 4),
)

val testTagTitleOnlyList: List<String> = listOf(
    "test1", "test2", "test3", "test4"
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