package com.bussiness.cassanova.ui.component.sheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.CommonWhiteBorderButton
import com.bussiness.cassanova.ui.screen.main.CalendarScreen
import com.bussiness.cassanova.ui.theme.gradientBrush

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpdateReservationDateSheet(onDismiss: () -> Unit = {},
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


        // Main dialog content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .background(
                    Color.Black,
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )
                .padding(horizontal = 21.dp, vertical = 30.dp)
        ) {
            // Title
            Text(
                text = "Update Reservation Date",
                style = TextStyle(
                    brush = gradientBrush
                ),
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
            )

            val selectedDate = CalendarScreen()

            Spacer(modifier = Modifier.height(30.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CommonWhiteBorderButton(
                    title = "Cancel",
                    modifier = Modifier.weight(1f).height(46.dp),
                    onClick = {
                        onDismiss()
                    }
                )

                CommonButton(
                    title = "Update",
                    modifier = Modifier.weight(1f).height(46.dp),
                    onClick = {onSubmitClick()}
                )
            }
            Spacer(modifier = Modifier.height(25.dp))

        }
    }

}