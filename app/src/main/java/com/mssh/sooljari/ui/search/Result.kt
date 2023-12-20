package com.mssh.sooljari.ui.search

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.Alcohol
import com.mssh.sooljari.model.AlcoholRepository
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.components.ResultCard
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel,
    query: String,
    results: List<Alcohol>,
    onResultCardClick: (Long) -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsState()

    val searchedQuery = rememberUpdatedState(newValue = query).value

    when {
        results.isEmpty() && !isLoading -> {
            NoResult(
                modifier = modifier,
                query = searchedQuery
            )
        }

        else -> {
            SearchResults(
                modifier = modifier,
                viewModel = viewModel,
                query = searchedQuery,
                isLoading = isLoading,
                results = results,
                onResultCardClick = onResultCardClick
            )
        }
    }
}

@Composable
private fun SearchResults(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel,
    query: String,
    isLoading: Boolean,
    results: List<Alcohol>,
    onResultCardClick: (Long) -> Unit
) {
    val listState = rememberLazyListState()

    //스크롤이 마지막에 닿으면 추가 데이터 로드
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .collect { lastIndex ->
                if (lastIndex != null && viewModel.canLoadMore) {
                    val totalItemCount = listState.layoutInfo.totalItemsCount

                    if (lastIndex >= totalItemCount - 3) {
                        viewModel.loadMoreList()
                    }
                }
            }
    }


    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.neutral0)
            ),
        state = listState,
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 8.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(results) {
            ResultCard(
                alcohol = it,
                query = query,
                onResultCardClick = onResultCardClick
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.2.dp),
                color = colorResource(id = R.color.neutral2)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
        }

        if (isLoading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(52.dp),
                    color = colorResource(id = R.color.purple3),
                )
            }
        }
    }


}

@Composable
fun NoResult(
    modifier: Modifier = Modifier,
    query: String
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.neutral1)
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\' $query \'",
            color = colorResource(id = R.color.purple3),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "에 대한 검색 결과가 없어요.",
            color = colorResource(id = R.color.neutral5_alpha75),
            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier,
            shape = RoundedCornerShape(3.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.purple3)
            )
        ) {
            Text(
                text = "새로운 술 기록하기"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xF8F8F8)
@Composable
private fun SearchResultsPreview() {
    SearchResults(
        modifier = Modifier
            .fillMaxSize(),
        viewModel = AlcoholViewModel(AlcoholRepository(), Application()),
        query = "한노아",
        isLoading = true,
        results = emptyList(),
        onResultCardClick = {}
    )
}

@Preview
@Composable
private fun NoResultPreview() {
    NoResult(query = "한노아")
}