package com.bussiness.cassanova.ui.component.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.MenuItem
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun MenuItemDetailSheet( onDismiss: () -> Unit = {},
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
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),

                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_dinner_table),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(  modifier = Modifier
                .fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Truffle Parmesan Fries",
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    color = Color.White,
                    fontSize = 24.sp
                )
                Text(
                    text = "Appetizers",
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    color = TextAAColor,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = "$25",
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    style = TextStyle(
                        brush = gradientBrush
                    ),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,

                    )
                Text(
                    text = "Hand-cut fries tossed in white truffle oil, parmesan, and herb salt. Hand-cut fries tossed in white truffle oil, parmesan, and herb salt.",
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,

                    )

                Spacer(Modifier.height(20.dp))
            }

        }

    }

}