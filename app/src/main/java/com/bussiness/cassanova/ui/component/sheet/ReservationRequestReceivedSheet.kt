package com.bussiness.cassanova.ui.component.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.bussiness.cassanova.ui.component.input.CommonButton
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.TextWhite

@Composable
fun ReservationRequestReceivedSheet(onDismiss: () -> Unit = {},
                                    onSubmitClick: () -> Unit = {},){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_cross_icon),
            contentDescription = "Close",
            modifier = Modifier
                .clickable {
                    onDismiss()
                }
                .size(40.dp)
                .clip(CircleShape)

        )

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                //.align(Alignment.BottomCenter)
                .background(Color.Black)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_phil_check_icon),
                contentDescription = "",
                modifier = Modifier.wrapContentSize()
            )
            Spacer(Modifier.height(20.dp))
            Text(text = "Reservation Request Received",
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                lineHeight = 40.sp
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Thank you for reserving your place in the\n Cassanova experience.",
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                color = TextAAColor,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
            Text(
                text = "Our team will contact you shortly via email or phone to confirm your table reservation and assign your Table ID.",
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                color = TextWhite,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )


            Spacer(Modifier.height(20.dp))

            CommonButton(
                title = "Okay, Thanks",
                fontSize = 18.sp,
                modifier = Modifier.height(46.dp).padding(horizontal = 20.dp),
                onClick = {
                    onSubmitClick()
                })
            Spacer(Modifier.height(30.dp))

        }

    }
}