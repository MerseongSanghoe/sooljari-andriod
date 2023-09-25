package com.mssh.sooljari.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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

@Preview
@Composable
private fun ChipsPreview() {
    SoolJariTheme {
        Column {
            SearchBarTagChip()
            SearchBarTagChip(tagString = stringResource(id = R.string.tag_long))
        }
    }
}