package com.mssh.sooljari.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R

@Composable
fun SearchView(
    onNavigateToHome: () -> Unit
) {
    Column {
        SearchAppBar(onNavigateToHome)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.neutral0)),
        ) {

        }
    }
}

@Composable
private fun SearchAppBar(
    onNavigateToHome: () -> Unit
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    var text by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .padding(top = statusBarHeight)
                .matchParentSize()
        ) {
            /*
            TODO: Button 따로 componets에 빼기
             */

            Button(
                modifier = Modifier
                    .size(32.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.neutral0)
                ),
                contentPadding = PaddingValues(0.dp),
                onClick = onNavigateToHome,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null
                )
            }

            SearchTextField(
                text = text,
                onTextChanged = { newText ->
                    text = newText
                },
                modifier = Modifier
                    .weight(1f)
            )

            Button(
                modifier = Modifier
                    .size(32.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.neutral0)
                ),
                contentPadding = PaddingValues(0.dp),
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun SearchTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        modifier = modifier,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxHeight()
                    .background(
                        color = colorResource(id = R.color.neutral0_alpha65),
                        shape = RoundedCornerShape(3.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.neutral0),
                        shape = RoundedCornerShape(3.dp)
                    )
                    .padding(horizontal = 8.dp)
            ) {
                innerTextField()
            }
        },
        cursorBrush = SolidColor(colorResource(id = R.color.black)),
        singleLine = true
    )
}

@Preview(heightDp = 48)
@Composable
private fun SearchAppBarPreview() {
    val home: () -> Unit = {}
    SearchAppBar(home)
}

@Preview
@Composable
private fun SearchViewPreview() {
    val home: () -> Unit = {}
    SearchView(home)
}