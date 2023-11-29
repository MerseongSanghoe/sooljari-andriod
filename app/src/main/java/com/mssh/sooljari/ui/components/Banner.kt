package com.mssh.sooljari.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.mssh.sooljari.R
import com.mssh.sooljari.model.Image
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun Banner(
    modifier: Modifier = Modifier,
    imageList: List<Image>,
    isAutoScroll: Boolean = true
) {
    val pageCount = imageList.size
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })

    //3초마다 다음 배너로 넘어감
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            //스크롤이 Int.MAX 보다 큰 경우 처리
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % Int.MAX_VALUE)
        }
    }

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) { index ->
            //실제 사용할 페이지 인덱스
            val pageIndex = index % pageCount

            val url = if (imageList[pageIndex].url.orEmpty().contains("http://")
                || imageList[pageIndex].url.orEmpty().contains("https://")
            ) {
                imageList[pageIndex].url
            } else {
                "http://211.37.148.214${imageList[pageIndex].url}"
            }

            GlideImage(
                model = url,
                contentDescription = null,
                modifier = modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.FillHeight,
                failure = placeholder(R.drawable.img_placeholder)
            )
        }

        //인디케이터
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                //실제 사용되는 페이지 인덱스
                val pageIndex = pagerState.currentPage % pageCount

                val color =
                    if (pageIndex == iteration) {
                        colorResource(id = R.color.neutral0)
                    } else {
                        colorResource(id = R.color.neutral5_alpha25)
                    }
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                        .clip(CircleShape)
                        .background(color = color)
                        .size(8.dp)
                )
            }
        }
    }


}

@Preview
@Composable
fun BannerPreview() {
    Banner(
        Modifier,
        listOf(
            Image(
                url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
            ),
            Image(
                url = "http://211.37.148.214/uploads/product_slowly_n2_069521a90a.jpg"
            )
        )
    )
}