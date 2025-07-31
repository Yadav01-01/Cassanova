package com.bussiness.cassanova.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.BookingItem
import okhttp3.internal.wait

@Composable
fun LoyaltyPointsBanner(
    points: Int,
    onRedeemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp) // Fixed height to control image position
            .background(Color.Black, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        // Gift image at bottom-end, overlapping
        Image(
            painter = painterResource(id = R.drawable.gift_pack_ic),
            contentDescription = "Gift Pack",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp, end = 130.dp) // leave room for image
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.gift_ic),
                    contentDescription = "Gift Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        ) {
                            append("You Have ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFC7A65E),
                                        Color(0xFFFBE29A),
                                        Color(0xFFBE9B43)
                                    )
                                )
                            )
                        ) {
                            append("$points ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        ) {
                            append("Loyalty Points")
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.i_btn),
                    contentDescription = "Info Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { /* Show tooltip */ }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Use your points to save on food & drinks (excluding alcohol).",
                fontSize = 14.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFAAAAAA),
                fontFamily = FontFamily(Font(R.font.urbanist_semibold))
            )

            Spacer(modifier = Modifier.height(12.dp))

            GradientButton(
                text = "Reserve & Redeem",
                onClick = onRedeemClick,
                modifier = Modifier.width(180.dp)
            )
        }
    }
}





@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFC7A65E),
                        Color(0xFFFBE29A),
                        Color(0xFFBE9B43)
                    )
                )
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.urbanist_semibold))
        )
    }
}


@Composable
fun OrderCard(
    orderNumber: String,
    date: String,
    time: String,
    redeemedPoints: String,
    amount: String,
    itemsCount: Int,
    earnedPoints: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(vertical = 10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Black, shape = RoundedCornerShape(8.dp)) // Background first
                .dashedBorder( // Then border
                    strokeWidth = 1.dp,
                    cornerRadius = 8.dp
                )
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Order No. $orderNumber",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 20.sp
                    )
                    Text(
                        text = "$$amount",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 20.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "$date – $time",
                        color = Color(0xFFAAAAAA),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "$itemsCount items",
                        color = Color(0xFFAAAAAA),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        append("You earned ")
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFFFFC940),
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                                fontSize = 14.sp
                            )
                        ) {
                            append(earnedPoints)
                        }
                        append(" points from this reservation.")
                    },
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )
            }
        }

        // Redeemed Points Badge
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = (-10).dp, x = (-10).dp)
                .background(brush = Brush.horizontalGradient(colors = listOf(Color(0xFFC7A65E), Color(0xFFFBE29A), Color(0xFFBE9B43))), shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "$redeemedPoints Points Redeemed",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun BookingSelection(
    modifier: Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background = if (selected) {
        Brush.linearGradient(
            colors = listOf(
                Color(0xFFC7A65E),
                Color(0xFFFBE29A),
                Color(0xFFBE9B43)
            )
        )
    } else {
        SolidColor(Color(0xFF191919))
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(background)
            .clickable(onClick = onClick)
            .height(39.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
            fontSize = 16.sp,
            color = if (selected) Color.Black else Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}



@SuppressLint("SuspiciousModifierThen")
fun Modifier.dashedBorder(
    strokeWidth: Dp,
    cornerRadius: Dp = 0.dp,
    dashLength: Dp = 8.dp,
    gapLength: Dp = 4.dp
): Modifier = this.then(
    Modifier.drawBehind {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashLength.toPx(), gapLength.toPx())
            )
        )

        val gradientBrush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFC7A65E),
                Color(0xFFFBE29A),
                Color(0xFFBE9B43)
            ),
            start = Offset.Zero,
            end = Offset(size.width, size.height)
        )

        drawRoundRect(
            brush = gradientBrush,
            style = stroke,
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )
    }
)


@Composable
fun BookingCard(
    booking: BookingItem,
    onStatusClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFC7A65E),
            Color(0xFFFBE29A),
            Color(0xFFBE9B43)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .dashedBorder(
                strokeWidth = 1.dp,
                cornerRadius = 8.dp
            )
            .padding(16.dp)
    ) {
        Column {
            // Title Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = booking.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 20.sp
                    )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.height(25.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(gradientBrush)
                    ) {
                        Text(
                            text = booking.status,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 12.sp
                        )
                    }

                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }

            // Date & Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = booking.time,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    ),
                    color = Color(0xFFAAAAAA)
                )

                Text(
                    text = "${booking.guestCount} Guest",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    ),
                    color = Color.White
                )
            }


            Spacer(modifier = Modifier.height(6.dp))

            // Special Request
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Special Request",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    ),
                    color = Color(0XFFAAAAAA)
                )
                Text(
                    text = booking.specialRequest,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    ),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Bottom message
            val parts = booking.message.split(booking.highlightText)
            Row {
                Text(
                    text = parts.getOrNull(0) ?: "",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    ),
                )
                Text(
                    text = booking.highlightText,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        fontSize = 18.sp,
                        brush = Brush.linearGradient(colors = listOf(Color(0xFFC7A65E), Color(0xFFFBE29A), Color(0xFFBE9B43))),
                    ),
                )
                Text(
                    text = parts.getOrNull(1) ?: "",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}



@Preview
@Composable
fun PreviewPointsBanner() {
    LoyaltyPointsBanner(120, onRedeemClick = { })
}