package com.mssh.sooljari.ui.home.navigation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.ui.theme.SoolJariTheme

@Composable
fun NavigationButton(
    icon: Painter,
    description: String,
    descriptionColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(38.dp)
            .clickable(
                onClick = onClick
            )
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .height(20.dp)
                .width(35.56.dp)
        )

        Text(
            text = description,
            color = descriptionColor,
            fontSize = 9.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    Surface(
        shadowElevation = 1.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(color = colorResource(id = R.color.neutral0))
        ) {
            val context = LocalContext.current

            NavigationButton(
                icon = painterResource(id = R.drawable.ic_menu),
                description = stringResource(id = R.string.navi_description_menu),
                onClick = {
                    Toast.makeText(context, "준비중 입니다", Toast.LENGTH_SHORT).show()
                }
            )

            NavigationButton(
                icon = painterResource(id = R.drawable.ic_search_star),
                description = stringResource(id = R.string.navi_description_find)
            )

            NavigationButton(
                icon = painterResource(id = R.drawable.ic_home_filled),
                description = stringResource(id = R.string.navi_description_home),
                descriptionColor = colorResource(id = R.color.purple4)
            )

            NavigationButton(
                icon = painterResource(id = R.drawable.ic_user),
                description = stringResource(id = R.string.navi_description_user),
                onClick = {
                    Toast.makeText(context, "준비중 입니다", Toast.LENGTH_SHORT).show()
                }
            )

            NavigationButton(
                icon = painterResource(id = R.drawable.ic_review),
                description = stringResource(id = R.string.navi_description_review),
                onClick = {
                    Toast.makeText(context, "준비중 입니다", Toast.LENGTH_SHORT).show()
                }
            )

        }
    }
}


@Preview
@Composable
fun NavigationButtonPreview() {
    NavigationButton(
        icon = painterResource(id = R.drawable.ic_home),
        description = stringResource(id = R.string.navi_description_home)
    )
}

@Preview
@Composable
fun BottomNavigationPreview() {
    SoolJariTheme {
        NavigationBar(Modifier.fillMaxWidth())
    }
}