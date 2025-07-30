package com.bussiness.cassanova.ui.screen.main.reverse

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.SummaryHeader
import com.bussiness.cassanova.ui.component.dialog.ModifyGuestSizeRequestsDialog
import com.bussiness.cassanova.ui.component.dialog.ReservationConfirmedDialog
import com.bussiness.cassanova.ui.component.dialog.ReservationRequestReceivedDialog
import com.bussiness.cassanova.ui.component.dialog.UpdateReservationDateDialog
import com.bussiness.cassanova.ui.component.dialog.UpdateReservationTimeDialog
import com.bussiness.cassanova.ui.screen.main.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservationSummaryScreen(navController: NavHostController) {
    var showCalenderDialog by remember { mutableStateOf(false) }
    var showSlotTimeDialog by remember { mutableStateOf(false) }
    var showModifyGuestSizeRequestsDialog by remember { mutableStateOf(false) }
    var showReservationRequestReceivedDialog by remember { mutableStateOf(false) }
    var showReservationConfirmedDialog by remember { mutableStateOf(false) }
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFC7A65E),
            Color(0xFFFBE29A),
            Color(0xFFBE9B43)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {

        SummaryHeader(title = "Summary", onBackClick = {
            navController.popBackStack()
        })


        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 30.dp)) {


            // Summary Items
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SummaryItem(
                    icon = R.drawable.ic_date_time_icon,
                    title = "Date & Time",
                    value = "Saturday, 27 May - 8:00 PM",
                    gradientBrush = gradientBrush,
                    onClick = { showCalenderDialog = true }
                )

                SummaryItem(
                    icon = R.drawable.ic_user_outline,
                    title = "Guest Count",
                    value = "2 Guests",
                    gradientBrush = gradientBrush,
onClick = {showSlotTimeDialog = true}
                )

                SummaryItem(
                    icon = R.drawable.ic_special_event,
                    title = "Special Request",
                    value = "Birthday Setup",
                    gradientBrush = gradientBrush,
                    onClick = {showModifyGuestSizeRequestsDialog = true}
                )
            }

            Spacer(Modifier.height(30.dp))

            CommonButton(
                title = "Submit Reservation Request",
                fontSize = 18.sp,
                modifier = Modifier.height(52.dp),
                onClick = {
                    showReservationRequestReceivedDialog = true
                })
        }

    }

    if(showCalenderDialog){
        UpdateReservationDateDialog(onDismiss={showCalenderDialog = false}, onSubmitClick = {
            showCalenderDialog = false
        })
    }

    if(showSlotTimeDialog){
        UpdateReservationTimeDialog(onDismiss={showSlotTimeDialog = false}, onSubmitClick = {
            showSlotTimeDialog = false})
    }
    if(showModifyGuestSizeRequestsDialog){
        ModifyGuestSizeRequestsDialog(onDismiss={showModifyGuestSizeRequestsDialog = false}, onSubmitClick = {
            showModifyGuestSizeRequestsDialog = false})
    }

    if(showReservationRequestReceivedDialog){
        ReservationRequestReceivedDialog(onDismiss={showReservationRequestReceivedDialog = false}, onSubmitClick = {showReservationRequestReceivedDialog = false
            showReservationConfirmedDialog = true
        })
    }
    if(showReservationConfirmedDialog){
        ReservationConfirmedDialog(onDismiss={showReservationConfirmedDialog = false}, viewButtonClick = {showReservationConfirmedDialog = false})
    }
}

@Composable
fun SummaryItem(
    icon: Int,
    title: String,
    value: String,
    gradientBrush: Brush,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(71.dp)
            .drawBehind {
                drawRoundRect(
                    brush = gradientBrush,
                    size = size,
                    cornerRadius = CornerRadius(4.dp.toPx()),
                    style = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(30f, 11f),
                            phase = 0f
                        )
                    )
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 10.dp),

            ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {


                Row {
                    Image(
                        painter = painterResource(icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)

                    )

                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    )

                }


                Text(
                    text = value,
                    color = Color(0xFFD9D9D9),
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    fontSize = 14.sp
                )


            }
            Image(
                painter = painterResource(id = R.drawable.ic_edit_icon),
                contentDescription = "Edit",
                modifier = Modifier
                    .size((18).dp)
                    .align(Alignment.CenterEnd)
                    .clickable{
                        onClick()
                    }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ReservationSummaryScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        ReservationSummaryScreen(navController)
    }
}