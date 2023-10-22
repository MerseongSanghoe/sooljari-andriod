package com.mssh.sooljari.ui.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.components.ResultCard

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    query: String,
    searchedQuery: MutableState<String>
) {
    val results by viewModel.alcoholResults.collectAsState()
    Log.d("Search Result result", "$results")

    if (results?.data?.size == 0) {
        Log.d("no result found", "no result")

        NoResult(
            modifier = modifier,
            queryState = searchedQuery
        )
    } else {
        Log.d("result found", "start lazyCol")

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = colorResource(id = R.color.neutral1)
                ),
            contentPadding = PaddingValues(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(results?.data.orEmpty()) {
                ResultCard(alcohol = it, keyword = query)
            }
        }
    }
}

@Composable
fun NoResult(
    modifier: Modifier = Modifier,
    queryState: MutableState<String>
) {
    val searchedQuery = rememberUpdatedState(newValue = queryState.value).value

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
            text = "\' $searchedQuery \'",
            color = colorResource(id = R.color.purple3),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "에 대한 검색 결과가 없어요.",
            color = colorResource(id = R.color.neutral3_alpha75),
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

@Preview
@Composable
private fun SearchResultsPreview() {
    //SearchResults(Modifier.fillMaxSize(), viewModel(), "")
}

@Preview
@Composable
private fun NoResultPreview() {
    NoResult(queryState = remember { mutableStateOf("한노아") })
}