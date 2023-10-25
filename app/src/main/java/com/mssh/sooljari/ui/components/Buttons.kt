package com.mssh.sooljari.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mssh.sooljari.R

@Composable
fun TransparentIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter,
    iconColor: Color = Color.Black,
    buttonSize: Dp,
    iconSize: Dp
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(buttonSize),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = iconColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = iconColor
        )
    }
}

@Preview
@Composable
private fun TransparentIconButtonPreview() {
    TransparentIconButton(
        onClick = {},
        modifier = Modifier,
        icon = painterResource(R.drawable.ic_star),
        iconColor = Color.White,
        buttonSize = 48.dp,
        iconSize = 24.dp
    )
}