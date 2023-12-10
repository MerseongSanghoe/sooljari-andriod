package com.mssh.sooljari.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.mssh.sooljari.R
import com.mssh.sooljari.model.Alcohol
import com.mssh.sooljari.model.addHash
import com.mssh.sooljari.model.testTagTitleOnlyList
import kotlin.random.Random

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun VerticalCard(
    modifier: Modifier,
    alcohol: Alcohol,
    keyword: String = "",
    onCardClick: (Long) -> Unit
) {
    val name = alcohol.name?.trimStart() ?: stringResource(id = R.string.error_no_value)
    val category = alcohol.category?.trimStart() ?: stringResource(id = R.string.error_no_value)
    val staredNumber = remember { String.format("%,d", Random.nextInt(0, 3000)) }
    val thumbnail = if (alcohol.imageUrl.isNullOrEmpty()) {
        R.drawable.img_placeholder
    } else {
        "http://211.37.148.214${alcohol.imageUrl}"
    }

    Card(
        modifier = modifier
            .width(160.dp)
            .wrapContentHeight()
            .clickable(onClick = { alcohol.id?.let { onCardClick(it) } }),
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.neutral0)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        // 썸네일
        GlideImage(
            model = thumbnail,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(3.dp))
                .background(
                    color = colorResource(id = R.color.neutral5_alpha15)
                ),
            contentScale = ContentScale.Fit,
            failure = placeholder(R.drawable.img_placeholder),
            contentDescription = null
        )

//본문
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //술 정보
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // title
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                )

                // category
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = category,
                    color = colorResource(id = R.color.neutral4),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                val tagList = addHash(alcohol.tags)

                TagListLazyRows(
                    tagStringList = tagList,
                    chip = textTagChip,
                    paddingBetweenChips = 2.dp,
                    rowNum = 1,
                )
            }

            //별표
            Row(
                modifier = modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                TransparentIconButton(
                    onClick = { /*TODO*/ },
                    icon = painterResource(id = R.drawable.ic_star),
                    iconColor = colorResource(id = R.color.neutral3),
                    buttonSize = 20.dp,
                    iconSize = 18.dp
                )

                Text(
                    modifier = modifier
                        .wrapContentSize(),
                    text = "${staredNumber}명이 별표", /*TODO*/
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.neutral3)
                )
            }
        }
    }
}

@Composable
fun HorizontalCard(
    alcohol: Alcohol,
    keyword: String = "",
    titleMaxLines: Int = 1,
    contentMaxLines: Int = 2,
    onCardClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = { alcohol.id?.let { onCardClick(it) } }),
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.neutral0)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_placeholder),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .aspectRatio(1f)
                    .background(
                        color = colorResource(id = R.color.neutral5_alpha15)
                    ),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )

            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "${alcohol.name ?: R.string.error_no_value}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = titleMaxLines
                    )

                    Text(
                        text = "${alcohol.category ?: R.string.error_no_value}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )

                    val tagList = addHash(alcohol.tags)

                    TagListLazyRows(
                        tagStringList = tagList,
                        chip = resultCardChip,
                        paddingBetweenChips = 4.dp,
                        rowNum = 1,
                        paddingBetweenRows = 4.dp,
                    )

                    Text(
                        text = stringResource(id = R.string.placeholder_long),/*TODO*/
                        fontSize = 10.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = contentMaxLines
                    )
                }
            }
        }
    }
}

@Composable
fun FairCard() {

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ResultCard(
    alcohol: Alcohol,
    query: String,
    onResultCardClick: (Long) -> Unit
) {
    val cardHeight = 140.dp

    val thumbnail = if (alcohol.imageUrl.isNullOrEmpty()) {
        R.drawable.img_placeholder
    } else {
        "http://211.37.148.214${alcohol.imageUrl}"
    }
    val name = alcohol.name?.trimStart() ?: stringResource(id = R.string.error_no_value)
    val category = alcohol.category?.trimStart() ?: stringResource(id = R.string.error_no_value)
    val degree = alcohol.degree?.let { "${it}도" } ?: stringResource(id = R.string.error_no_value)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = { alcohol.id?.let { onResultCardClick(it) } }),
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.neutral0)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            GlideImage(
                model = thumbnail,
                modifier = Modifier
                    .size(cardHeight)
                    .background(
                        color = colorResource(id = R.color.neutral0)
                    ),
                contentScale = ContentScale.FillHeight,
                failure = placeholder(R.drawable.img_placeholder),
                contentDescription = null
            )

            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$category  $degree",
                        fontSize = 16.sp,
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )

                    //하이라이트 할 태그들이 앞쪽으로 재배치된 태그 리스트
                    val tagList = addHash(reorderTagList(alcohol.tags, query))

                    //하이라이트 할 키워드들
                    val keywordList = query.split(" ").map { it.removePrefix("#") }

                    TagListLazyRows(
                        modifier = Modifier.fillMaxWidth(),
                        tagStringList = tagList,
                        chip = resultCardChip,
                        paddingBetweenChips = 4.dp,
                        rowNum = 2,
                        paddingBetweenRows = 4.dp,
                        keywordList = keywordList
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(18.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

//하이라이트 할 태그들을 리스트 앞으로 가져옴
private fun reorderTagList(tagList: List<String>, query: String): List<String> {
    val words = query.split(" ").map { it.removePrefix("#") }
    val keywordList = mutableListOf<String>()

    words.reversed().forEach { word ->
        if (tagList.contains(word)) {
            keywordList.add(word)
        }
    }

    tagList.forEach { word ->
        if (!keywordList.contains(word)) {
            keywordList.add(word)
        }
    }

    return keywordList
}

@Preview
@Composable
fun ResultCardPreview() {
    ResultCard(
        Alcohol(
            id = 0L,
            name = "기네스 흑맥주 말고 엄청나게 긴 이름",
            category = "맥주",
            degree = 4.3f,
            tags = testTags
        ),
        query = "유하민",
        onResultCardClick = {}
    )
}

@Preview
@Composable
fun VerticalCardPreview() {
    VerticalCard(
        modifier = Modifier,
        keyword = "플레이브",
        alcohol = Alcohol(
            id = 0L,
            name = "기네스 컴포즈 기네스 컴포즈기네스 컴포즈",
            category = "맛있어보이는 흑맥주",
            degree = 4.3f,
            tags = testTagTitleOnlyList
        ),
        onCardClick = {})
}

@Preview
@Composable
fun HorizontalCardPreview() {
    Box(
        modifier = Modifier
            .height(110.dp)
    ) {
        HorizontalCard(
            keyword = "플레이브",
            alcohol = Alcohol(
                id = 0L,
                name = "기네스 컴포즈 기네스 컴포즈기네스 컴포즈",
                category = "맛있어보이는 흑맥주",
                degree = 4.3f,
                tags = testTags
            ),
            onCardClick = {})
    }
}
