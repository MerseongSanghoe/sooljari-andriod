package com.mssh.sooljari.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.Alcohol
import com.mssh.sooljari.model.AlcoholRepository
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.components.HorizontalCard
import com.mssh.sooljari.ui.components.VerticalCard
import com.mssh.sooljari.ui.components.VerticalCardContainer
import com.mssh.sooljari.ui.components.testTags

@Composable
fun Feed(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel,
    onVerticalCardClick: (Long) -> Unit
) {
    val tag = "와인"
    val alcoholList = viewModel.alcoholListByTag.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAlcoholsByTag(tag)
    }

    Column(
        modifier = modifier
            .background(color = colorResource(id = R.color.neutral1))
            .fillMaxSize()
    ) {
        VerticalCardContainer(
            headerTitle = "요즘 인기있는 #$tag",
            alcoholList = alcoholList.value?.data ?: emptyList(),
            onCardClick = onVerticalCardClick
        )
    }
}



@Preview
@Composable
fun FeedPreview() {
    Feed(
        modifier = Modifier,
        viewModel = AlcoholViewModel(AlcoholRepository()),
        onVerticalCardClick = {}
    )
}