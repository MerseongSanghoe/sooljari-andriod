package com.mssh.sooljari.ui.home

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholRepository
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.model.testImageList
import com.mssh.sooljari.ui.components.Banner
import com.mssh.sooljari.ui.components.HorizontalCardContainer
import com.mssh.sooljari.ui.components.VerticalCardContainer

@Composable
fun Feed(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel,
    onVerticalCardClick: (Long) -> Unit,
    onNavigateToSearchByQuery: (String) -> Unit
) {
    val tags = listOf("와인", "단맛", "막걸리", "탁주", "신맛")
    val alcoholList = viewModel.alcoholListByTag.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAlcoholsByTags(tags)
    }

    Column(
        modifier = modifier
            .background(color = colorResource(id = R.color.neutral1))
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Banner(
            modifier = Modifier
                .fillMaxWidth(),
            imageList = testImageList
        )

        //콘텐츠들
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            VerticalCardContainer(
                headerTitle = "요즘 인기있는 #와인",
                alcoholList = alcoholList.value["와인"]?.data ?: emptyList(),
                onCardClick = onVerticalCardClick,
                onMoreButtonClick = { onNavigateToSearchByQuery("#와인") }
            )

            HorizontalCardContainer(
                headerTitle = "이런 #단맛 술을 추천해요",
                alcoholList = alcoholList.value["단맛"]?.data ?: emptyList(),
                onCardClick = onVerticalCardClick,
                onMoreButtonClick = { onNavigateToSearchByQuery("#단맛") }
            )

            VerticalCardContainer(
                headerTitle = "오늘은 #막걸리 어때요?",
                alcoholList = alcoholList.value["막걸리"]?.data ?: emptyList(),
                onCardClick = onVerticalCardClick,
                onMoreButtonClick = { onNavigateToSearchByQuery("#막걸리") }
            )

            HorizontalCardContainer(
                headerTitle = "많이 찾아본 #탁주",
                alcoholList = alcoholList.value["탁주"]?.data ?: emptyList(),
                onCardClick = onVerticalCardClick,
                onMoreButtonClick = { onNavigateToSearchByQuery("#탁주") }
            )

            VerticalCardContainer(
                headerTitle = "#신맛 태그와 연관된",
                alcoholList = alcoholList.value["신맛"]?.data ?: emptyList(),
                onCardClick = onVerticalCardClick,
                onMoreButtonClick = { onNavigateToSearchByQuery("#신맛") }
            )
        }
    }
}



@Preview
@Composable
fun FeedPreview() {
    Feed(
        modifier = Modifier,
        viewModel = AlcoholViewModel(AlcoholRepository(), Application()),
        onVerticalCardClick = {},
        onNavigateToSearchByQuery = {}
    )
}