package com.mssh.sooljari.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.AlcoholInfo
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.model.Image
import com.mssh.sooljari.model.Maker
import com.mssh.sooljari.model.addHash
import com.mssh.sooljari.model.tagListToStringList
import com.mssh.sooljari.model.testTagList
import com.mssh.sooljari.ui.components.Banner
import com.mssh.sooljari.ui.components.TagListLazyRows
import com.mssh.sooljari.ui.components.TransparentIconButton
import com.mssh.sooljari.ui.components.randomBackgroundChip

@Composable
fun AlcoholDetailView(
    modifier: Modifier = Modifier,
    alcoholId: Long,
    viewModel: AlcoholViewModel,
    onBackButtonClick: () -> Unit
) {
    val alcoholInfo by viewModel.alcoholInfo.collectAsState()

    LaunchedEffect(alcoholId) {
        viewModel.getAlcoholInfo(alcoholId)
    }

    when (alcoholInfo) {
        null -> {
            Text(
                text = "Loading...",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        else -> {
            AlcoholDetailView(
                modifier = modifier,
                alcoholInfo = alcoholInfo!!,
                onBackButtonClick = onBackButtonClick
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlcoholDetailView(
    modifier: Modifier = Modifier,
    alcoholInfo: AlcoholInfo,
    onBackButtonClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AlcoholDetailAppBar(
                modifier = modifier,
                onBackButtonClick = onBackButtonClick,
                scrollBehavior = scrollBehavior
            )
        }
    ) { PaddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = PaddingValues.calculateBottomPadding())
        ) {
            //배너 표시 또는 placeholder 이미지 표시
            if (alcoholInfo.imageList == null
                || alcoholInfo.imageList == emptyList<Image>()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_placeholder),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(280.dp)
                )
            } else {
                Banner(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    imageList = alcoholInfo.imageList
                )
            }

            //술 정보 표시 영역
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    //술 이름
                    Row {
                        Text(
                            text = alcoholInfo.title
                                ?: stringResource(R.string.error_no_value),
                            modifier = Modifier
                                .weight(1f),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                        )
                    }

                    //양조장
                    Row {
                        Text(
                            text = alcoholInfo.maker?.name
                                ?: stringResource(R.string.error_no_value),
                            modifier = Modifier
                                .weight(1f),
                            color = colorResource(id = R.color.neutral3),
                            fontSize = 16.sp,
                            maxLines = 1,
                        )
                    }

                    //주종, 도수
                    Row {
                        Text(
                            text = "${alcoholInfo.category ?: ""}, ${alcoholInfo.degree ?: 0}도",
                            modifier = Modifier
                                .weight(1f),
                            color = colorResource(id = R.color.neutral3),
                            fontSize = 16.sp,
                            maxLines = 1,
                        )
                    }
                }

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.neutral3),
                    thickness = 0.25.dp
                )

                //태그 리스트
                val tagList = addHash(
                    tagListToStringList(alcoholInfo.tagList ?: emptyList())
                )

                TagListLazyRows(
                    tagStringList = tagList,
                    chip = randomBackgroundChip,
                    paddingBetweenChips = randomBackgroundChip.horizontalPadding,
                    rowNum = 3,
                    paddingBetweenRows = 8.dp,
                )

                //술 정보
                AlcoholInfo(
                    modifier = modifier
                        .fillMaxWidth(),
                    alcoholInfo = alcoholInfo
                )
            }


        }
    }
}

@Composable
private fun AlcoholInfo(
    modifier: Modifier = Modifier,
    alcoholInfo: AlcoholInfo
) {
    /*
    val degree = alcoholInfo.degree
        ?: stringResource(id = R.string.error_no_value)
    val category = alcoholInfo.category
        ?: stringResource(id = R.string.error_no_value)
    val maker = alcoholInfo.maker?.name
        ?: stringResource(id = R.string.error_no_value)
     */
    val explanation = alcoholInfo.explanation
        ?: stringResource(id = R.string.error_no_value)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.neutral0)
            ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = modifier
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = explanation,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlcoholDetailAppBar(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    CenterAlignedTopAppBar(
        title = { /*TODO*/ },
        modifier = Modifier,
        navigationIcon = {
            TransparentIconButton(
                onClick = onBackButtonClick,
                icon = painterResource(R.drawable.ic_arrow_left),
                iconColor = colorResource(R.color.black),
                buttonSize = 32.dp,
                iconSize = 24.dp
            )
        },
        actions = {
            TransparentIconButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_share),
                iconColor = colorResource(R.color.black),
                buttonSize = 32.dp,
                iconSize = 28.dp
            )

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
            )

            TransparentIconButton(
                onClick = {},
                icon = painterResource(R.drawable.ic_search),
                iconColor = colorResource(R.color.black),
                buttonSize = 32.dp,
                iconSize = 24.dp
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.White
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AlcoholDetailAppBarPreview() {
    AlcoholDetailAppBar(Modifier, {}, TopAppBarDefaults.pinnedScrollBehavior())
}

@Composable
private fun alcoholInfoPreview(): AlcoholInfo {
    return AlcoholInfo(
        title = "PLAVE",
        degree = 12.3f,
        maker = Maker(
            name = "VLAST"
        ),
        category = "우주 최강 아이도루",
        explanation = stringResource(id = R.string.placeholder_long),
        tagList = testTagList
    )
}

@Preview
@Composable
private fun AlcoholDetailPreview() {
    AlcoholDetailView(
        modifier = Modifier,
        alcoholInfo = alcoholInfoPreview(),
        onBackButtonClick = {}
    )
}