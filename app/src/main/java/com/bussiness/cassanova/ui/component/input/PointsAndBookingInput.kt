package com.bussiness.cassanova.ui.component.input

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.BookingItem
import com.bussiness.cassanova.model.Reservation


@Composable
fun LoyaltyPointsBanner(
    points: Int,
    onRedeemClick: () -> Unit,
    onClickIcon: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Black, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))

    ) {

        // Top Row with Points Info and Info Icon
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
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
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                modifier = Modifier.weight(1f)
            )

            Icon(
                painter = painterResource(id = R.drawable.i_btn),
                contentDescription = "Info Icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onClickIcon() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Bottom Row with Text + Button and Gift Image
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(1f).padding(start = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Use your points to save on food & drinks (excluding alcohol).",
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
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

            Image(
                painter = painterResource(id = R.drawable.gift_pack_ic),
                contentDescription = "Gift Pack",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = 10.dp)

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
    Box(modifier = modifier.padding(vertical = 15.dp)) {
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
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
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
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFC7A65E),
                                            Color(0xFFFBE29A),
                                            Color(0xFFBE9B43)
                                        )
                                    )
                                )
                            ) {
                                append("$earnedPoints ")
                            }

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
                .offset(y = (-15).dp, x = (-12).dp)
                .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFC7A65E), Color(0xFFFBE29A), Color(0xFFBE9B43))), shape = RoundedCornerShape(5.dp))
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
    onCancelClick: () -> Unit
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFC7A65E),
            Color(0xFFFBE29A),
            Color(0xFFBE9B43)
        )
    )
    var menuExpanded by remember { mutableStateOf(false) }
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
                        modifier = Modifier.wrapContentHeight()
                            .clip(RoundedCornerShape(5.dp))
                            .background(gradientBrush),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = booking.status,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Icon with Dropdown
                    Box {
                        IconButton(onClick = { menuExpanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false },
                            offset = DpOffset(x = (-70).dp, y = 0.dp)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Cancel", color = Color.Black, fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.urbanist_medium))) },
                                onClick = {
                                    menuExpanded = false
                                    onCancelClick()
                                },
                                modifier = Modifier.height(25.dp).clip(RoundedCornerShape(5.dp)).padding(end =15.dp).width(70.dp)
                            )
                        }
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
                        brush = Brush.verticalGradient(colors = listOf(Color(0xFFC7A65E), Color(0xFFFBE29A), Color(0xFFBE9B43))),
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


@Composable
fun PreviousBanner(
    reservation: Reservation
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .dashedBorder(
                strokeWidth = 1.dp,
                cornerRadius = 8.dp,
            )
            .padding(16.dp)
    ) {
        Column {
            // Title Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Table ID - ${reservation.tableId}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${reservation.itemCount} items",
                        color = Color(0xFFAAAAAA),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "$${reservation.totalAmount}",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Date & Guest
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${reservation.date}, ${reservation.time}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    color = Color(0xFFAAAAAA)
                )

                Text(
                    text = "${reservation.guests} Guest",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Special Request
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Special Request",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFAAAAAA),
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )

                reservation.occasion?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Message with highlighted points
            val parts = reservation.message?.split(reservation.pointsEarned)
            Row {
                if (parts != null) {
                    Text(
                        text = parts.getOrNull(0) ?: "",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                            fontSize = 14.sp
                        ),
                    )
                }
                Text(
                    text = reservation.pointsEarned,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        fontSize = 18.sp,
                        brush = Brush.verticalGradient(colors = listOf(Color(0xFFC7A65E), Color(0xFFFBE29A), Color(0xFFBE9B43))),
                    ),
                )
                if (parts != null) {
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
}



//@Preview
//@Composable
//fun PreviewPointsBanner() {
//    val sampleReservation = Reservation(
//        tableId = "#A21",
//        date = "Monday, 2 June",
//        time = "10:00 AM",
//        itemCount = 2,
//        totalAmount = 150.0,
//        guests = 4,
//        specialRequest = "Special Request",
//        occasion = "Birthday Party",
//        occasionIcon = null,
//        pointsEarned = 15,
//        status = "Cancelled" // Optional
//    )
//
//    ReservationCard(reservation = sampleReservation)
//
//}