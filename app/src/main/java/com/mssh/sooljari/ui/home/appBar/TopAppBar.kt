package com.mssh.sooljari.ui.home.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.ui.components.SearchBarTagChip
import com.mssh.sooljari.ui.theme.SoolJariTheme
import com.mssh.sooljari.ui.theme.logoFont

private fun addHashTag(tagList: List<String>): List<String> {
    val tagAddedList: MutableList<String> = ArrayList(tagList)

    tagAddedList.forEachIndexed { index, tag ->
        tagAddedList[index] = "#$tag"
    }

    return tagAddedList
}

@Composable
fun LazyRowWithDynamicChips(
    tagStrings: List<String>,
    lazyRowWidth: Dp
) {
    val density = LocalDensity.current

    //받은 태그 목록에 해시태그 글자 추가
    val hashTaggedString: List<String> = addHashTag(tagStrings)
    //태그 사이 간격
    val betweenSapce: Dp = 8.dp

    var currentWidth: Dp = 0.dp



    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(betweenSapce),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(hashTaggedString) { tagString ->
            val chipWidth = remember(tagString) {
                val textWidth = with(density) {
                    (tagString.length * 10).sp.toDp()
                }
                // chip 모양 때문에 발생하는 패딩 추가
                textWidth + 24.dp
            }
            //chip 너비 + 태그 사이 간격
            if (currentWidth + (chipWidth + betweenSapce) <= lazyRowWidth) {
                SearchBarTagChip(tagString = tagString)
                currentWidth += (chipWidth + betweenSapce)
            } else {
                // 만약 새로운 chip 너비 + 기존 chip들 너비 합이 lazyRowWidth 보다 크면 새로운 칩을 추가 하지 않음
                return@items
            }
        }
    }
}

private val testTags: List<String> =
    listOf(
        "일곱글자태그임",
        "여섯글자태그",
        "다섯자태그",
        "네자태그",
        "세글자"
    )


@Composable
fun TopAppBar() {

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_main),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier.matchParentSize()
        )

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = logoFont,
                fontSize = 24.sp,
                color = colorResource(id = R.color.neutral0)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

            Search()

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

            LazyRowWithDynamicChips(
                tagStrings = testTags,
                lazyRowWidth = 336.dp
            )
        }
    }


}


@Preview
@Composable
fun TopAppbarPreview() {
    SoolJariTheme {
        TopAppBar()
    }
}