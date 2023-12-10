package com.mssh.sooljari.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlin.random.Random

enum class Chips {
    SEARCH_BAR_TAG,
    RESULT_CARD_TAG,
    DEFAULT_TAG,
    LIGHT_TAG,
    RANDOM_BACKGROUND_TAG,
    TEXT_TAG
}

data class Chip(
    val chipType: Chips,
    val fontSize: TextUnit,
    val horizontalPadding: Dp
)

val searchBarTagChip: Chip =
    Chip(Chips.SEARCH_BAR_TAG, 12.sp, 8.dp)
val resultCardChip: Chip =
    Chip(Chips.RESULT_CARD_TAG, 14.sp, 4.dp)
val defaultTagChip: Chip =
    Chip(Chips.DEFAULT_TAG, 14.sp, 8.dp)
val lightTagChip: Chip =
    Chip(Chips.LIGHT_TAG, 14.sp, 8.dp)
val randomBackgroundChip: Chip =
    Chip(Chips.RANDOM_BACKGROUND_TAG, 16.sp, 8.dp)
val textTagChip: Chip =
    Chip(Chips.TEXT_TAG, 14.sp, 0.dp)

@Composable
fun TextTagChip(
    tagString: String,
    onClick: () -> Unit = {}
) {
    Text(
        text = tagString,
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = Color.Transparent,
            )
            .padding(
                horizontal = textTagChip.horizontalPadding,
            )
            .clickable(
                onClick = onClick
            ),
        fontSize = textTagChip.fontSize
    )
}

@Composable
fun RandomBackgroundTagChip(
    tagString: String,
    onClick: () -> Unit = {}
) {
    val random = Random.nextInt(0, 8)

    val backgroundColor =
        when (random) {
            0 -> colorResource(id = R.color.purple0)
            1 -> colorResource(id = R.color.purple_200)

            else -> colorResource(id = R.color.neutral5_alpha15)
        }

    val textColor =
        when (random) {
            in 0..1 -> colorResource(id = R.color.purple5)

            else -> colorResource(id = R.color.black)
        }


    Text(
        text = tagString,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(3.dp),
            )
            .padding(
                horizontal = randomBackgroundChip.horizontalPadding,
                vertical = 2.dp
            )
            .clickable(
                onClick = onClick
            ),
        color = textColor,
        fontSize = randomBackgroundChip.fontSize
    )
}

@Composable
fun LightTagChip(
    tagString: String,
    onClick: () -> Unit = {}
) {
    Text(
        text = tagString,
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = Color.Transparent,
                shape = CircleShape
            )
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    color = colorResource(id = R.color.neutral2)
                ),
                shape = CircleShape
            )
            .padding(
                horizontal = lightTagChip.horizontalPadding,
                vertical = 2.dp
            )
            .clickable(
                onClick = onClick
            ),
        fontSize = lightTagChip.fontSize
    )
}

@Composable
fun DefaultTagChip(
    tagString: String,
    onClick: () -> Unit = {}
) {
    Text(
        text = tagString,
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = Color.Transparent,
                shape = CircleShape
            )
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.purple4)
                ),
                shape = CircleShape
            )
            .padding(
                horizontal = defaultTagChip.horizontalPadding,
                vertical = 2.dp
            )
            .clickable(
                onClick = onClick
            ),
        fontSize = defaultTagChip.fontSize
    )
}

@Composable
fun SearchBarTagChip(
    tagString: String = stringResource(id = R.string.tag_short),
    onClick: () -> Unit = {}
) {
    val chipShape: Shape = CircleShape

    Text(
        text = tagString,
        modifier = Modifier
            //.wrapContentSize()
            .background(
                color = colorResource(id = R.color.neutral0_alpha15),
                shape = chipShape
            )
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.neutral0)
                ),
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = searchBarTagChip.horizontalPadding,
                vertical = 4.dp
            )
            .clickable(
                onClick = onClick
            ),
        color = colorResource(id = R.color.neutral0),
        fontSize = searchBarTagChip.fontSize,
    )
}

@Composable
fun ResultCardTagChip(
    tagString: String = stringResource(id = R.string.tag_short),
    isKeyword: Boolean = false,
    onClick: () -> Unit = {}
) {
    val backgroundColor =
        if (isKeyword) {
            colorResource(id = R.color.orange_0)
        } else {
            colorResource(id = R.color.neutral5_alpha15)
        }
    val textColor =
        if (isKeyword) {
            colorResource(id = R.color.orange_6)
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
            .padding(
                horizontal = resultCardChip.horizontalPadding
            ),
        color = textColor,
        fontSize = resultCardChip.fontSize
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
 * @param chip 표시될 칩 종류
 * @param paddingBetweenChips 칩 사이 간격
 * @param rowNum 태그가 표시될 행의 총 갯수
 * @param paddingBetweenRows 행 사이 간격
 * @param onClickChip 칩 클릭시 수행하는 동작, 모든 칩에 일괄 적용됨, parameter로 tagString을 사용
 * @param keywordList 특별히 강조해야 할 태그들
 */

@Composable
fun TagListLazyRows(
    modifier: Modifier = Modifier,
    tagStringList: List<String>,
    chip: Chip,
    paddingBetweenChips: Dp = 0.dp,
    rowNum: Int = 1,
    paddingBetweenRows: Dp = 0.dp,
    onClickChip: (String) -> Unit = {},
    keywordList: List<String> = emptyList()
) {
    //레이아웃 크기 계산 관련 변수들
    val density = LocalDensity.current
    val rowWidth = remember { mutableStateOf(0.dp) }
    val tagListRows = mutableListOf<List<String>>()

    var sumOfChipWidth = 0.dp
    var tagList = mutableListOf<String>()

    tagStringList.forEach { tagString ->
        val chipWidth = with(density) {
            (tagString.length * chip.fontSize.value).sp.toDp() + (chip.horizontalPadding * 2)
        }

        if (sumOfChipWidth + chipWidth <= rowWidth.value) {
            tagList.add(tagString)
            sumOfChipWidth += (chipWidth + paddingBetweenChips)
        } else {
            tagListRows.add(tagList)
            tagList = mutableListOf(tagString)
            sumOfChipWidth = chipWidth
        }
    }

    if (tagList.isNotEmpty()) {
        tagListRows.add(tagList)
    }

    if (tagListRows.size > rowNum) {
        tagListRows.subList(rowNum, tagListRows.size).clear()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .onGloballyPositioned { coordinates ->
                rowWidth.value = with(density) { coordinates.size.width.toDp() }
            }
    ) {
        tagListRows.forEachIndexed { index, tagListRow ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(paddingBetweenChips)
            ) {
                tagListRow.forEach { tag ->
                    AddChip(
                    	chipType = chip.chipType, 
                    	tagString = tag,
                        keywordList = keywordList,
                        onClick = { onClickChip(tag) }
                    )
                }
            }

            if (index < tagListRows.size) {
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(paddingBetweenRows)
                )
            }
        }
    }
}

@Composable
private fun AddChip(
    chipType: Chips,
    tagString: String,
    keywordList: List<String>,
    onClick: () -> Unit = {},
) {
    return when (chipType) {
        Chips.SEARCH_BAR_TAG -> {
            SearchBarTagChip(tagString, onClick)
        }

        Chips.RESULT_CARD_TAG -> {
            if (keywordList.contains(tagString.removePrefix("#"))) {
                ResultCardTagChip(tagString, true, onClick)
            } else ResultCardTagChip(tagString, false, onClick)
        }

        Chips.DEFAULT_TAG -> {
            DefaultTagChip(tagString, onClick)
        }

        Chips.LIGHT_TAG -> {
            LightTagChip(tagString, onClick)
        }

        Chips.RANDOM_BACKGROUND_TAG -> {
            RandomBackgroundTagChip(tagString, onClick)
        }

        Chips.TEXT_TAG -> {
            TextTagChip(tagString, onClick)
        }
    }
}

val testTags: List<String> =
    listOf(
        "일곱글자태그임",
        "여섯글자태그",
        "다섯자태그",
        "네자태그",
        "세글자",
        "괜히늘려보기",
        "플레이브",
        "남예준",
        "한노아",
        "채밤비",
        "도은호",
        "유하민"
    )

val testTagsRecommend: List<String> =
    listOf(
        "와인",
        "막걸리",
        "오미자",
        "탁주",
        "단맛",
        "신맛",
        "약주",
        "생막걸리",
        "떠먹는막걸리",
        "이화주"
    )

@Preview(showBackground = true, backgroundColor = 0xF0FFFFFF, widthDp = 200)
@Composable
private fun TagListLazyRowPreview() {
    TagListLazyRows(
        tagStringList = testTags,
        chip = resultCardChip,
        keywordList = emptyList(),
        rowNum = 2,
        paddingBetweenRows = 4.dp
    )
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

@Preview
@Composable
private fun DefaultTagChipPreview() {
    Column {
        DefaultTagChip(tagString = "#태그")
        DefaultTagChip(tagString = "#엄청나게긴태그")
    }
}

@Preview
@Composable
private fun LightTagChipPreview() {
    Column {
        LightTagChip(tagString = "#태그")
        LightTagChip(tagString = "#엄청나게긴태그")
    }
}

@Preview
@Composable
private fun RandomBackgroundTagChipPreview() {
    Column {
        testTags.forEach {
            RandomBackgroundTagChip(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextTagChipPreview() {
    Column {
        TextTagChip(tagString = "#태그")
        TextTagChip(tagString = "#엄청나게긴태그")
    }
}
