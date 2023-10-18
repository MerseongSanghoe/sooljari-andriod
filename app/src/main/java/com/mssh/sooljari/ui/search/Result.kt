package com.mssh.sooljari.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.mssh.sooljari.R

@Composable
fun SearchResults(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.neutral0)
            )
    ) {

    }
}

@Composable
fun NoResult() {
    Text(
        text = "결과 없음"
    )
}

@Preview
@Composable
private fun SearchResultsPreview() {
    SearchResults(Modifier.fillMaxSize())
}