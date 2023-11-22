package com.mssh.sooljari.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.SearchedByTagAlcohol
import com.mssh.sooljari.model.testSearchedByTagAlcoholList

@Composable
fun CardListContainerHeader(
    headerTitle: String,
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
                .clickable { },
            color = colorResource(id = R.color.purple3),
            fontSize = 14.sp,
        )
    }
}

@Composable
fun VerticalCardContainer(
    headerTitle: String,
    alcoholList: List<SearchedByTagAlcohol> = emptyList(),
    onCardClick: (Long) -> Unit
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
            headerTitle = headerTitle
        )

        //카드 리스트
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(alcoholList) { index, alcohol ->
                VerticalCard(
                    alcohol = alcohol,
                    onCardClick = onCardClick
                )

                if (index < (alcoholList.size - 1)) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(8.dp)
                    )

                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(0.3.dp),
                        color = colorResource(id = R.color.neutral3)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardListContainerHeaderPreview() {
    CardListContainerHeader(
        headerTitle = "오늘의 추천"
    )
}

@Preview
@Composable
private fun VerticalCardContainerPreview() {
    VerticalCardContainer(
        headerTitle = "오늘의 추천",
        alcoholList = testSearchedByTagAlcoholList,
        onCardClick = {}
    )
}
