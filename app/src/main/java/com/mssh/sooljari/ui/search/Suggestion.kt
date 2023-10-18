package com.mssh.sooljari.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.mssh.sooljari.R

@Composable
fun SearchSuggestions(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.neutral0)
            )
    ) {
        Text(
            text = "최근 검색 기록"
        )

        Text(
            text = "인기 태그"
        )

        Text(
            text = "태그 랭킹"
        )
    }
}

@Preview
@Composable
private fun SearchSuggestionsPreview() {
    SearchSuggestions(Modifier.fillMaxSize())
}