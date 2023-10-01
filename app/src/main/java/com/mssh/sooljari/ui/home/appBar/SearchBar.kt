package com.mssh.sooljari.ui.home.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.ui.theme.SoolJariTheme


@Composable
fun Search() {
    SearchBar()
}

@Composable
private fun SearchBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(
                color = colorResource(id = R.color.neutral0_alpha55),
                shape = RoundedCornerShape(3.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.neutral0),
                shape = RoundedCornerShape(3.dp)
            )
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            tint = colorResource(id = R.color.neutral0),
            contentDescription = null)

        Spacer(modifier = Modifier
            .fillMaxHeight()
            .width(8.dp)
        )

        Divider(
            color = colorResource(id = R.color.neutral0),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Spacer(modifier = Modifier
            .fillMaxHeight()
            .width(8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.searchbar_hint),
                color = colorResource(id = R.color.neutral0),
                fontSize = 12.sp,
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
            )
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    SoolJariTheme {
        SearchBar()
    }
}