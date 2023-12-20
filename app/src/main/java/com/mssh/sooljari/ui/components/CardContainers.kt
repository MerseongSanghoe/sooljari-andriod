package com.mssh.sooljari.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.Alcohol
import com.mssh.sooljari.model.SearchedByTagAlcohol
import com.mssh.sooljari.model.testSearchedByTagAlcoholList

@Composable
fun CardListContainerHeader(
    headerTitle: String,
    onMoreButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = headerTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "+ 더보기",
            modifier = Modifier
                .clickable(
                    onClick = onMoreButtonClick
                ),
            color = colorResource(id = R.color.purple3),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun VerticalCardContainer(
    headerTitle: String,
    alcoholList: List<Alcohol> = emptyList(),
    onCardClick: (Long) -> Unit,
    onMoreButtonClick: () -> Unit,
) {
    val density = LocalDensity.current
    val cardHeight = remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.neutral0)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        //헤더
        CardListContainerHeader(
            headerTitle = headerTitle,
            onMoreButtonClick = onMoreButtonClick
        )

        //카드 리스트
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .onGloballyPositioned {
                    cardHeight.value = with(density) { it.size.height.toDp() }
                },
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(alcoholList) { index, alcohol ->
                VerticalCard(
                    modifier = Modifier
                        .wrapContentHeight(),
                    alcohol = alcohol,
                    onCardClick = onCardClick
                )

                if (index < (alcoholList.size - 1)) {
                    Spacer(
                        modifier = Modifier
                            .height(cardHeight.value)
                            .width(8.dp)
                    )

                    Divider(
                        modifier = Modifier
                            .height(cardHeight.value)
                            .width(0.25.dp),
                        color = colorResource(id = R.color.neutral2)
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalCardContainer(
    headerTitle: String,
    alcoholList: List<Alcohol> = emptyList(),
    onCardClick: (Long) -> Unit,
    onMoreButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.neutral0)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        CardListContainerHeader(
            headerTitle = headerTitle,
            onMoreButtonClick = onMoreButtonClick
        )

        alcoholList.take(5)
            .forEach { alcohol ->
            HorizontalCard(
                alcohol = alcohol,
                onCardClick = onCardClick
            )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
        }
    }
}

@Preview
@Composable
private fun CardListContainerHeaderPreview() {
    CardListContainerHeader(
        headerTitle = "오늘의 추천",
        onMoreButtonClick = {}
    )
}

@Preview
@Composable
private fun VerticalCardContainerPreview() {
    VerticalCardContainer(
        headerTitle = "오늘의 추천",
        alcoholList = testSearchedByTagAlcoholList,
        onCardClick = {},
        onMoreButtonClick = {}
    )
}

@Preview
@Composable
private fun HorizontalCardContainerPreview() {
    HorizontalCardContainer(
        headerTitle = "오늘의 추천",
        alcoholList = testSearchedByTagAlcoholList,
        onCardClick = {},
        onMoreButtonClick = {}
    )
}
