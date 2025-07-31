package com.bussiness.cassanova.ui.component.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R

@Composable
fun CancellationReasonSheet(onClose: () -> Unit){
    Box(modifier = Modifier.fillMaxSize()) {
        // Bottom Sheet Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.Black)
                .padding(15.dp)
                .padding(top = 22.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cancel_ic),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.wrapContentSize().align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Cancellation Reason",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                PrivilegeItem("Use points next time (non-alcohol items only).")
                PrivilegeItem("1$ earned per $10 spent.")
                PrivilegeItem("Exclusive invites, curated just for you.")
            }
        }

        // Floating Close Button
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-260).dp)
                .background(Color.Black, shape = CircleShape)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cross_icon),
                contentDescription = "Close",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
fun CancelPreview(){
    CancellationReasonSheet {  }
}