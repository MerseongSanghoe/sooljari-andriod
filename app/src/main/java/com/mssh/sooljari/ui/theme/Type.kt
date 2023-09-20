package com.mssh.sooljari.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R

// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        )
        /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val logoFont = FontFamily(Font(R.font.hakgyoansimwooju_r))

val nanumSquare = FontFamily(
        Font(R.font.nanumsquare_ac_r, FontWeight.Normal),
        Font(R.font.nanumsquare_ac_b, FontWeight.Bold),
        Font(R.font.nanumsquare_ac_eb, FontWeight.ExtraBold),
        Font(R.font.nanumsquare_ac_l, FontWeight.Light)
        )