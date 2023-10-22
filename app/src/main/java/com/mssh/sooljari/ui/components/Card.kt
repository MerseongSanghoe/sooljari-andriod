package com.mssh.sooljari.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mssh.sooljari.R
import com.mssh.sooljari.model.Alcohol

@Composable
fun VerticalCard() {

}

@Composable
fun HorizontalCard() {

}

@Composable
fun FairCard() {

}

@Composable
fun ResultCard(
    alcohol: Alcohol,
    keyword: String
) {
    val cardHeight = 140.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.neutral0)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_placeholder),
                modifier = Modifier
                    .size(cardHeight)
                    .background(
                        color = colorResource(id = R.color.neutral3_alpha15)
                    ),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )

            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "${alcohol.name ?: R.string.error_no_value}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${alcohol.category ?: R.string.error_no_value}" +
                                " ${alcohol.degree ?: 0f}도",
                        fontSize = 14.sp,
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )

                    TagListLazyRows(
                        tagStringList = alcohol.tags,
                        chip = resultCardChip,
                        paddingBetweenChips = 4.dp,
                        rowNum = 2,
                        paddingBetweenRows = 4.dp,
                        keyword = keyword
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(18.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ResultCardPreview() {
    ResultCard(
        Alcohol(
            id = 0L,
            name = "기네스 흑맥주 말고 엄청나게 긴 이름",
            category = "맥주",
            degree = 4.3f,
            tags = testTags
        ),
        keyword = "유하민"
    )
}