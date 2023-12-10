package com.mssh.sooljari.ui.search

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholRepository
import com.mssh.sooljari.model.AlcoholViewModel

@Composable
fun SearchKeywords(
    modifier: Modifier = Modifier,
    viewModel: AlcoholViewModel,
    query: String,
    onKeywordClick: (String) -> Unit
) {
    val keywords by viewModel.keywordList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAutocompleteKeywords()
    }

    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.neutral0)
            )
            .padding(horizontal = 56.dp)
            .padding(top = 10.dp)
    ) {
        keywords?.forEach {
            QueryHighlightedText(
                modifier = Modifier.fillMaxWidth(),
                keyword = it,
                query = query,
                onKeywordClick = onKeywordClick
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
        }
    }
}

/**
 * 자동완성 키워드에서 사용된 검색어를 강조하는 함수
 *
 * @param keyword API로 받아온 자동완성 키워드
 * @param query 사용자가 입력한 검색어
 */
@Composable
private fun QueryHighlightedText(
    modifier: Modifier = Modifier,
    keyword: String,
    query: String,
    onKeywordClick: (String) -> Unit
) {
    /*
    참고 링크
    https://developer.android.com/jetpack/compose/text/style-paragraph#multiple-paragraph-styles
     */

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 16.sp)) {
            val startIndex = keyword.indexOf(query)
            val endIndex = startIndex + query.length

            if (startIndex >= 0) {
                append(keyword.substring(0, startIndex))
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.purple3),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(keyword.substring(startIndex, endIndex))
                }
                append(keyword.substring(endIndex))
            } else {
                append(keyword)
            }
        }
    }

    Text(
        text = annotatedString,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onKeywordClick(annotatedString.text) }),
    )
}

@Preview
@Composable
private fun KeywordsPreview() {
    SearchKeywords(
        modifier = Modifier.fillMaxSize(),
        viewModel = AlcoholViewModel(AlcoholRepository(), Application()),
        query = "검색어",
        onKeywordClick = {}
    )
}