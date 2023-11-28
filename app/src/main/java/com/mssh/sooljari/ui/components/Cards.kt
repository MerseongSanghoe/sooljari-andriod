package com.mssh.sooljari.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.mssh.sooljari.model.SearchedByTagAlcohol
import com.mssh.sooljari.model.Tag
import com.mssh.sooljari.model.addHash
import com.mssh.sooljari.model.tagListToStringList
import kotlin.random.Random

@Composable
fun VerticalCard(
    alcohol: SearchedByTagAlcohol,
    keyword: String = "",
    titleMaxLines: Int = 1,
    onCardClick: (Long) -> Unit
) {
    val name = alcohol.name ?: stringResource(id = R.string.error_no_value)
    val category = alcohol.category ?: stringResource(id = R.string.error_no_value)

    Card(
        modifier = Modifier
            .size(
                width = 160.dp,
                height = 260.dp
            )
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
        Image(
            painter = painterResource(id = R.drawable.img_placeholder),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(3.dp))
                .background(
                    color = colorResource(id = R.color.neutral5_alpha15)
                ),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )

        // title
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            overflow = TextOverflow.Ellipsis,
            maxLines = titleMaxLines
        )

        // category
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            text = category,
            fontSize = 14.sp,
        )

        val tagList = addHash(tagListToStringList(alcohol.relatedTags))

        TagListLazyRows(
            tagStringList = tagList,
            chip = resultCardChip,
            paddingBetweenChips = 4.dp,
            rowNum = 1,
            keyword = keyword,
            paddingBetweenRows = 4.dp
        )

        //별표
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(18.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 2.dp),
                text = "${String.format("%,d", Random.nextInt(0, 3000))}명이 별표", /*TODO*/
                fontSize = 14.sp,
                color = Color.Gray
            )
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
                        keyword = keyword
                    )

                    Text(
                        text = "${alcohol.name}${alcohol.name}${alcohol.name}${alcohol.name}${alcohol.name}${alcohol.name}",/*TODO*/
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
    keyword: String,
    onResultCardClick: (Long) -> Unit
) {
    val cardHeight = 140.dp

    val thumbnail = if (alcohol.imageUrl.isNullOrEmpty()) {
        R.drawable.img_placeholder
    } else {
        "http://211.37.148.214${alcohol.imageUrl}"
    }
    val name = alcohol.name ?: stringResource(id = R.string.error_no_value)
    val category = alcohol.category ?: stringResource(id = R.string.error_no_value)
    val degree = if (alcohol.degree == null) {
        ""
    } else {
        alcohol.degree.toString() + "도"
    }

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
                contentScale = ContentScale.Fit,
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
                        .wrapContentHeight()
                ) {
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$category  $degree",
                        fontSize = 14.sp,
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
                        rowNum = 2,
                        paddingBetweenRows = 4.dp,
                        keyword = keyword
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
        keyword = "유하민",
        onResultCardClick = {}
    )
}

@Preview
@Composable
fun VerticalCardPreview() {
    VerticalCard(
        keyword = "플레이브",
        alcohol = SearchedByTagAlcohol(
            id = 0L,
            name = "기네스 컴포즈 기네스 컴포즈기네스 컴포즈",
            category = "맛있어보이는 흑맥주",
            degree = 4.3f,
            relatedTags = listOf(
                Tag("테스트 태그", 1),
                Tag("무언가", 1),
                Tag("아이우에오", 1),
                Tag("머리아프다", 1),
                Tag("라라라", 1),
                Tag("영어", 1),
            )
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
