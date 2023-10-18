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
fun SearchKeywords(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.neutral0)
            )
    ) {
        Text(
            text = "대충 검색어"
        )
    }
}

@Preview
@Composable
private fun KeywordsPreview() {
    SearchKeywords(Modifier.fillMaxSize())
}