package com.bussiness.cassanova.ui.screen.main.reverse

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.theme.TextAAColor

@Composable
fun TimeSlotButton(
    time: String,
    isSelected: Boolean,
    isDisabled: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(enabled = !isDisabled, onClick = onClick)
            .then(
                when {
                    isSelected -> Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFC7A65E),
                                    Color(0xFFFBE29A),
                                    Color(0xFFBE9B43)
                                )
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )

                    isDisabled -> Modifier
                        .border(
                            width = 1.dp,
                            color = TextAAColor,
                            shape = RoundedCornerShape(8.dp)
                        )
                    else -> Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = time,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
            color = when {
                isSelected -> Color.Black
                isDisabled -> TextAAColor
                else -> Color.White
            }
        )
    }
}