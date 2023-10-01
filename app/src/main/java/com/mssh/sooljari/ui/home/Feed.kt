package com.mssh.sooljari.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.mssh.sooljari.R

@Composable
fun Feed(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = colorResource(id = R.color.neutral1))
            .fillMaxSize()
    ) {
        Text(
            text = "Feed"
        )
    }
}

@Preview
@Composable
fun FeedPreview() {
    Feed(Modifier.fillMaxWidth())
}