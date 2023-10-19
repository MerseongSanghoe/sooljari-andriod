package com.mssh.sooljari.ui.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.components.ResultCard

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    query: String
) {
    val results by viewModel.alcoholResults.collectAsState()
    Log.d("Search Result result", "$results")

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.neutral1)
            ),
        contentPadding = PaddingValues(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(results?.data.orEmpty()) {
            ResultCard(
                alcohol = it,
                keyword = query
            )
        }
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
    SearchResults(Modifier.fillMaxSize(), viewModel(), "")
}