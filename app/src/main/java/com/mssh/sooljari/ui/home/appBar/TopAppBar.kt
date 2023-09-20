package com.mssh.sooljari.ui.home.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.mssh.sooljari.ui.theme.logoFont

@Composable
private fun TopAppBar() {

    Box {
        Image(
            painter = painterResource(id = R.drawable.bg_main),
            contentDescription = null)

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
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