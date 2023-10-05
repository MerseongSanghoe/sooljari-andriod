package com.mssh.sooljari.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.ui.theme.SoolJariTheme

@Composable
fun SearchBarTagChip(
    tagString: String = stringResource(id = R.string.tag_short)
) {
    val chipShape: Shape = CircleShape

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = colorResource(id = R.color.neutral0_alpha15),
                shape = chipShape
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.neutral0),
                shape = chipShape
            )
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = tagString,
            color = colorResource(id = R.color.neutral0),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ResultCardTagChip(
    tagString: String = stringResource(id = R.string.tag_short),
    isKeyword: Boolean = false
) {
    val backgroundColor =
        if (isKeyword) {
            colorResource(id = R.color.purple0)
        } else {
            colorResource(id = R.color.neutral3_alpha15)
        }
    val textColor =
        if (isKeyword) {
            colorResource(id = R.color.purple5)
        } else {
            colorResource(id = R.color.black)
        }

    Text(
        text = tagString,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(3.dp),
            )
            .padding(horizontal = 4.dp),
        color = textColor,
        fontSize = 12.sp,
    )
}

/**
 * 태그들을 표시하는 함수
 *
 * 태그를 표시하되, 레이아웃 크기를 넘어가는 태그는 표시하지 않습니다.
 * 태그 문자열들을 담은 리스트와 태그가 표시될 컴포저블, 태그를 표시할 열 갯수에 따라
 * 태그들을 배열합니다.
 * 태그들은 가로로 배열되다 레이아웃 크기를  넘어가면 다음 행에 표시되거나
 * 다음 행이 없는 경우 더이상 배열되지 않습니다.
 *
 * @param tagStringList 태그 문자열을 담은 string 리스트
 * @param tagType 태그 문자열이 담길 컴포저블
 * @param rowNum 태그가 표시될 행의 총 갯수
 */

@Composable
fun TagListLazyColumn(
    tagStringList: List<String>,
    tagType: @Composable () -> Unit,
    rowNum: Int
) {

}

@Preview
@Composable
private fun SearchBarChipsPreview() {
    SoolJariTheme {
        Column {
            SearchBarTagChip()
            SearchBarTagChip(tagString = stringResource(id = R.string.tag_long))
        }
    }
}

@Preview
@Composable
private fun ResultCardChipsPreview() {
    Column {
        ResultCardTagChip()
        ResultCardTagChip(isKeyword = true)
    }
}