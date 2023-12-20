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
        url = "https://media.discordapp.net/attachments/1151138404130627624/1187148548769325056/1.PNG?ex=6595d53e&is=6583603e&hm=1685c0163d3c51c831d7c177fc3bf3c8a140fbb6e32549c610f2233dd3695752&=&format=webp&quality=lossless&width=895&height=671"
    ),
    Image(
        url = "https://media.discordapp.net/attachments/1151138404130627624/1187148549192962099/2.PNG?ex=6595d53e&is=6583603e&hm=11db01a7331774c108a78e91cc8be180e24f23139c10af6120727f67c6ac97f5&=&format=webp&quality=lossless&width=895&height=671"
    ),
    Image(
        url = "https://media.discordapp.net/attachments/1151138404130627624/1187148549654315089/3.PNG?ex=6595d53e&is=6583603e&hm=cca153e76471790e13dab26919822a7682ee3785a42fbc7d77f5552d42671677&=&format=webp&quality=lossless&width=895&height=671"
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