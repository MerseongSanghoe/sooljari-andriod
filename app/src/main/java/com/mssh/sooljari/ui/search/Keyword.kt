package com.mssh.sooljari.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SearchKeywords(
    modifier: Modifier = Modifier,
    query: String,
    keywords: List<String>
) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.neutral0)
            )
            .padding(horizontal = 56.dp)
    ) {
        keywords.forEach {
            QueryHighlightedText(
                modifier = Modifier.fillMaxWidth(),
                keyword = it,
                query = query
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
    query: String
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
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
private fun KeywordsPreview() {
    SearchKeywords(
        modifier = Modifier.fillMaxSize(),
        query = "검색어",
        keywords = listOf(
            "검색어 1",
            "검색어가 포함된 자동완성",
            "검색어   야호   3",
            "중간에 검색어가 있어도 괜찮나",
        )
    )
}