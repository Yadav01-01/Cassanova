package com.bussiness.cassanova.ui.screen.main.reverse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.SpecialRequest

@Composable
fun RequestChip(
    request: SpecialRequest,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (isSelected) Color.White else Color.Transparent
            )
            .border(
                1.dp,
                Color.White,
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            request.iconResId?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = request.name,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(10.dp))
            }

            Text(
                text = request.name,
                color = if (isSelected) Color.Black else Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}