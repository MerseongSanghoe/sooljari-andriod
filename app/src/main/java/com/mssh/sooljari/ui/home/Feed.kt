package com.mssh.sooljari.ui.home

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
import com.mssh.sooljari.ui.components.VerticalCardContainer

@Composable
fun Feed(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel,
    onVerticalCardClick: (Long) -> Unit,
    onNavigateToSearchByQuery: (String) -> Unit
) {
    val tags = listOf("와인", "막걸리", "신맛")
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
            for (tag in tags) {
                val clickFunc: () -> Unit = { onNavigateToSearchByQuery("#$tag") }
                VerticalCardContainer(
                    headerTitle = "요즘 인기있는 #$tag",
                    alcoholList = alcoholList.value[tag]?.data ?: emptyList(),
                    onCardClick = onVerticalCardClick,
                    onMoreButtonClick = clickFunc
                )
            }
        }
    }
}



@Preview
@Composable
fun FeedPreview() {
    Feed(
        modifier = Modifier,
        viewModel = AlcoholViewModel(AlcoholRepository()),
        onVerticalCardClick = {},
        onNavigateToSearchByQuery = {}
    )
}