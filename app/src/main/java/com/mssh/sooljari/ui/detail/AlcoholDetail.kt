package com.mssh.sooljari.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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
    onBackButtonClick: () -> Unit,
    onTagClick: (String) -> Unit
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
                onBackButtonClick = onBackButtonClick,
                onTagClick = onTagClick
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
private fun AlcoholDetailView(
    modifier: Modifier = Modifier,
    alcoholInfo: AlcoholInfo,
    onBackButtonClick: () -> Unit,
    onTagClick: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(
                color = colorResource(id = R.color.neutral0)
            ),
        topBar = {
            AlcoholDetailAppBar(
                onBackButtonClick = onBackButtonClick,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = colorResource(id = R.color.neutral0))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TransparentIconButton(
                    onClick = {},
                    icon = painterResource(id = R.drawable.ic_star),
                    buttonSize = 32.dp,
                    iconSize = 28.dp
                )

                Divider(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .width(1.dp),
                    color = colorResource(id = R.color.neutral3)
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(id = R.color.purple3)
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = colorResource(id = R.color.purple3)
                    )
                ) {
                    Text(text = "리뷰 작성하기")
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            color = Color.Black
                        )
                ) {
                    GlideImage(
                        model = R.drawable.bg_button,
                        contentDescription = null,
                        modifier = Modifier
                            .matchParentSize(),
                        contentScale = ContentScale.FillBounds,
                        alpha = 0.85f
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .matchParentSize(),
                        shape = RoundedCornerShape(3.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_star),
                            contentDescription = null,
                            tint = Color.White
                        )

                        Text(text = "비슷한 술 찾기")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
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
                    //양조장
                    Row {
                        Text(
                            text = alcoholInfo.maker?.name
                                ?: stringResource(R.string.error_no_value),
                            modifier = Modifier
                                .weight(1f),
                            color = colorResource(id = R.color.neutral3),
                            fontSize = 14.sp,
                            maxLines = 1,
                        )
                    }

                    //술 이름
                    Row {
                        Text(
                            text = alcoholInfo.title?.trimStart()
                                ?: stringResource(R.string.error_no_value),
                            modifier = Modifier
                                .weight(1f),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                        )
                    }

                    //주종, 도수
                    Row {
                        Text(
                            text = "${alcoholInfo.category ?: ""}, ${alcoholInfo.degree ?: 0}도",
                            modifier = Modifier
                                .weight(1f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                        )
                    }
                }

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.neutral3),
                    thickness = 0.2.dp
                )

                //태그 리스트
                val tagList = addHash(
                    tagListToStringList(alcoholInfo.tagList ?: emptyList())
                )

                TagListLazyRows(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    tagStringList = tagList,
                    chip = randomBackgroundChip,
                    paddingBetweenChips = randomBackgroundChip.horizontalPadding,
                    rowNum = 3,
                    paddingBetweenRows = 8.dp,
                    onClickChip = onTagClick
                )

                //술 정보
                AlcoholInfo(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    alcoholInfo = alcoholInfo
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(
                        color = colorResource(id = R.color.neutral1)
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("리뷰")

                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.purple3),
                                    )
                                ) {
                                    append(" 0")
                                }
                            }
                        }
                    )

                    Text(
                        text = "+ 더보기",
                        modifier = Modifier
                            .clickable(
                                onClick = {}
                            ),
                        color = colorResource(id = R.color.purple3),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "아직 리뷰가 없어요 :(",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(5.dp))
                        .background(
                            color = colorResource(id = R.color.neutral1)
                        )
                        .padding(vertical = 20.dp),
                    color = colorResource(id = R.color.neutral2),
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
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
        modifier = modifier,
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
        onBackButtonClick = {},
        onTagClick = {}
    )
}