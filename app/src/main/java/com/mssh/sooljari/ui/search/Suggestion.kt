package com.mssh.sooljari.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.ui.components.TagListLazyRows
import com.mssh.sooljari.ui.components.TransparentIconButton
import com.mssh.sooljari.ui.components.defaultTagChip
import com.mssh.sooljari.ui.components.lightTagChip
import com.mssh.sooljari.ui.components.testTags

@Composable
fun SearchSuggestions(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.neutral0)
            )
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SearchHistory()

        PopularTags()

        TagRanking()
    }
}

@Composable
private fun SearchHistory(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        //헤더
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "최근 검색 기록",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            TransparentIconButton(
                onClick = { /*TODO*/ },
                icon = painterResource(id = R.drawable.ic_more),
                buttonSize = 24.dp,
                iconSize = 22.dp
            )
        }

        TagListLazyRows(
            tagStringList = testTags,
            chip = lightTagChip,
            paddingBetweenChips = 8.dp
        )
    }
}

@Composable
private fun PopularTags(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "인기 태그",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        TagListLazyRows(
            tagStringList = testTags,
            chip = defaultTagChip,
            paddingBetweenChips = 8.dp,
            rowNum = 3,
            paddingBetweenRows = 12.dp,
        )
    }
}

@Composable
private fun TagRanking(
    modifier: Modifier = Modifier,
    tagList: List<String> = testTags
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "지금 #태그 탑랭킹",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        //랭킹 표시
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            //1위부터 5위
            Column(
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (index in 0..4) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("${index + 1}")
                            }
                            append("\t\t")
                            append(tagList[index])
                        },
                        fontSize = 14.sp
                    )
                }
            }

            //6위부터 10위
            Column(
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (index in 5..9) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("${index + 1}")
                            }
                            append("\t\t")
                            append(tagList[index])
                        },
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchSuggestionsPreview() {
    SearchSuggestions(Modifier.fillMaxSize())
}