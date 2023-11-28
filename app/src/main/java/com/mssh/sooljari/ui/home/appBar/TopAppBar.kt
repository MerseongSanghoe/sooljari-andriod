package com.mssh.sooljari.ui.home.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mssh.sooljari.R
import com.mssh.sooljari.model.addHash
import com.mssh.sooljari.ui.components.TagListLazyRows
import com.mssh.sooljari.ui.components.searchBarTagChip
import com.mssh.sooljari.ui.components.testTags
import com.mssh.sooljari.ui.theme.SoolJariTheme
import com.mssh.sooljari.ui.theme.logoFont

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TopAppBar(
    onNavigateToSearch: () -> Unit
) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        GlideImage(
            model = R.drawable.bg_main,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(statusBarPadding.calculateTopPadding())
            )

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

            SearchBar(onNavigateToSearch)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

            val tagList = addHash(testTags)

            TagListLazyRows(
                tagStringList = tagList,
                chip = searchBarTagChip,
                paddingBetweenChips = 8.dp,
                rowNum = 1
            )
        }
    }
}

@Preview
@Composable
fun TopAppbarPreview() {
    SoolJariTheme {
        val search: () -> Unit = {}
        TopAppBar(search)
    }
}