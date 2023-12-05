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
        url = "https://i.namu.wiki/i/fW1CzsNKwgp9gh7bcZC5L-sz0ze5LmGI9CO-UA91altMXHB246OM-BOX9upSTERXHjyq3WW_OuL6G63T2gw24oQcdvOtMIsqtr36IQrjm5NAggOix_LzmsPzQmD1c68ujWKyvEyowKgSparlsoqMTg.webp"
    ),
    Image(
        url = "https://i.namu.wiki/i/P4jz7lEmjbtc8hvSo7HdyqurKJfTopTATFh_k6xZ4DZ-LraU3eBXXexaigQx27PUVGek-KpocuOcgBrOT2iAVFp192qrKcSQqMlVQkRO13yShCoKY6QFIL8jUiKUkFeyddrQfp4rbmrzFOTZTyS0kQ.webp"
    ),
    Image(
        url = "https://i.namu.wiki/i/CaD9Es4eheBSRRNG0_qOWIL29ROrEC1nurLNY8auh8maOPC1zMiJ8LGsBFa2GqqBZdkOze1I8NhMXs0HwyahMBUOEomk7drff7xPSeGqsQNYzGHh4uKxZjw-8THgza-M7Gtx_GTAfESqE3XhkHcAqA.webp"
    ),
    Image(
        url = "https://i.namu.wiki/i/F-2L3j9HXREG3XWw-d4ZXKRVk7dY7z08cG2SX0vaEUpbsO0WUYuIDGH0hR7r2qnz4LSx-ZZ1lQW_ERrsjV6HzZ0-PPsTgx2AEdvum2DzFO8BPCn-7O1Q_pO82mjXuHnr5zAxwuadkrXIjVXwkYLIOg.webp"
    ),
    Image(
        url = "https://i.namu.wiki/i/t_EWmZTqkk3UUj3dQ0yoHr8a8KCPJ42lLQ3gJM35KA-19C51y7GfCt_-T0dzdDJBD9mvxgoUWtyckTIlcqGvTcxkNUuCsznVvsK18HPc8oDRPKrrDxK-l-O38oQf2N7BsT9il2k-cSsA-U3hY2VvGQ.webp"
    ),
    Image(
        url = "https://i.namu.wiki/i/bXwn-O3BvTgwA-KnDHI36_vJqzYvgoZE9b8QqGtNPqE2YDJFD4h128fretUt25KQVZjxaxTCiCJazgoXeBxJyUhhVOSwc0AcoilZcOsPUAShhUS7FQNCdLaayDCzDItWrgsrZ6Pr7TFE4fb5tP3s3A.webp"
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