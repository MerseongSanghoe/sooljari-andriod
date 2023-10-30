package com.mssh.sooljari.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholDetail
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.components.TagListLazyRows
import com.mssh.sooljari.ui.components.TransparentIconButton
import com.mssh.sooljari.ui.components.resultCardChip
import com.mssh.sooljari.ui.components.testTags

@Composable
fun AlcoholDetailView(
    modifier: Modifier = Modifier,
    alcoholId: Long,
    viewModel: AlcoholViewModel
) {
    val alcoholDetail by viewModel.alcoholDetail.collectAsState()

    LaunchedEffect(alcoholId) {
        viewModel.getAlcoholDetail(alcoholId)
    }

    when (alcoholDetail) {
        null -> {
            Text(
                text = "Loading...",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        else -> {
            val alcohol = alcoholDetail!!.dataObject?.alcohol
            AlcoholDetailView(
                modifier = modifier,
                alcoholDetail = alcohol!!
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlcoholDetailView(
    modifier: Modifier = Modifier,
    alcoholDetail: AlcoholDetail
) {
    Scaffold(
        topBar = {
            AlcoholDetailAppBar(
                modifier = modifier,
                title = alcoholDetail.title
                    ?: stringResource(R.string.error_no_value)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            //배너 대신 임시 이미지
            Image(
                painter = painterResource(id = R.drawable.img_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(
                        color = colorResource(id = R.color.neutral1)
                    )
            )

            //술 정보 표시 영역
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                //태그 리스트
                TagListLazyRows(
                    tagStringList = alcoholDetail.tags ?: testTags,
                    chip = resultCardChip,
                    paddingBetweenChips = resultCardChip.horizontalPadding,
                    rowNum = 3,
                    paddingBetweenRows = 8.dp,
                )


            }



        }
    }
}

@Composable
private fun AlcoholDetailAppBar(
    modifier: Modifier = Modifier,
    title: String
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp + statusBarHeight)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_main),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .padding(top = statusBarHeight)
                .matchParentSize()
        ) {
            TransparentIconButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_arrow_left),
                iconColor = colorResource(R.color.neutral0),
                buttonSize = 32.dp,
                iconSize = 24.dp
            )

            Text(
                text = title,
                modifier = Modifier
                    .weight(1f),
                color = colorResource(R.color.neutral0),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            TransparentIconButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_star),
                iconColor = colorResource(R.color.neutral0),
                buttonSize = 32.dp,
                iconSize = 24.dp
            )
        }
    }


}

@Preview
@Composable
private fun AlcoholDetailAppBarPreview() {
    AlcoholDetailAppBar(Modifier, "술제목")
}

@Preview
@Composable
private fun AlcoholDetailPreview() {
    AlcoholDetailView(
        modifier = Modifier,
        alcoholDetail = AlcoholDetail(
            title = "제목",
            degree = 12f,

            )
    )
}