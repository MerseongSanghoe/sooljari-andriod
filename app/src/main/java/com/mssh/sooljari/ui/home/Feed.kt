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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.Alcohol
import com.mssh.sooljari.ui.components.HorizontalCard
import com.mssh.sooljari.ui.components.VerticalCard
import com.mssh.sooljari.ui.components.testTags

@Composable
fun Feed(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = colorResource(id = R.color.neutral1))
            .fillMaxSize()
    ) {
        Text(
            text = "Feed"
        )

        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(all = 8.dp)
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

        Row {
            Box(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .width(130.dp)
            ) {
                VerticalCard(
                    alcohol = Alcohol(
                        id = 0L,
                        name = "기네스 컴포즈 기네스 컴포즈기네스 컴포즈",
                        category = "맛있어보이는 흑맥주",
                        degree = 4.3f,
                        tags = testTags
                    ),
                    keyword = "플레이브",
                    onCardClick = {})
            }
        }
    }
}

@Preview
@Composable
fun FeedPreview() {
    Feed(Modifier.fillMaxWidth())
}