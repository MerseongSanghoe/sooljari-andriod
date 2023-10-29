package com.mssh.sooljari.ui.detail

import androidx.compose.foundation.Image
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
import com.mssh.sooljari.model.AlcoholViewModel
import com.mssh.sooljari.ui.components.TransparentIconButton

@OptIn(ExperimentalMaterial3Api::class)
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
            Scaffold(
                topBar = {
                    AlcoholDetailAppBar(
                        modifier = modifier,
                        title = alcoholDetail!!.dataObject?.alcohol?.title
                            ?: stringResource(R.string.error_no_value)
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    Text(
                        text = alcoholDetail!!.dataObject?.alcohol?.degree.toString()
                    )
                }
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
    //AlcoholDetailView(Modifier, AlcoholDetail())
}