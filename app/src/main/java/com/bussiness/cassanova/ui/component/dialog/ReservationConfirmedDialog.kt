package com.bussiness.cassanova.ui.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.theme.gradientBrush


@Composable
fun ReservationConfirmedDialog(
                        viewButtonClick: () -> Unit= {},
                        onDismiss: () -> Unit= {},
                        ){

    Dialog(
        onDismissRequest = {viewButtonClick()},
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x804C4C4C)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_cross_icon),
                    contentDescription = "Close",

                    modifier = Modifier
                        .clickable {
                            onDismiss()
                        }
                        .align(Alignment.TopCenter)
                        .size(40.dp)
                        .clip(CircleShape)
                        .padding(bottom = 8.dp)
                        .align(Alignment.TopStart)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp, top = 30.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth().padding(20.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Black),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        // Main content
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            // Icon (you can replace with your actual icon resource)

                            Spacer(Modifier.height(5.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_icons_party_icon),
                                contentDescription = "Party Icon",
                                modifier = Modifier
                                    .wrapContentSize()

                            )

                            // Title
                            Text(
                                text = "Reservation Confirmed",
                                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )

                            // Description
                            Text(
                                text = "Your table has been successfully reserved.",
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                                color = Color.White,
                                lineHeight = 5.sp
                            )

                            Text(
                                text = "Table ID - #A21",
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                 brush = gradientBrush
                                ),
                                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                                color = Color.White,
                                lineHeight = 5.sp
                            )

                            Text(
                                text = "See full details in your bookings.",
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                                color = Color.White,
                                lineHeight = 5.sp
                            )

                            Spacer(Modifier.height(5.dp))


                            CommonButton(
                                onClick = { viewButtonClick() },
                                title = "View Booking",
                                fontSize = 14.sp,
                                modifier = Modifier.width(141.dp).height(44.dp)
                            )
                            Spacer(Modifier.height(2.dp))
                        }
                    }
                }

            }

        }
    }

}



@Preview(showBackground = true)
@Composable
fun ReservationConfirmedDialogPreview() {
    ReservationConfirmedDialog()
}