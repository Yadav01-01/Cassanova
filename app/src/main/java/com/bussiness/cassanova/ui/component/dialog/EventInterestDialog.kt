package com.bussiness.cassanova.ui.component.dialog

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.input.CommonButton
import com.bussiness.cassanova.ui.theme.TextAAColor

@Composable
fun EventInterestDialog(
    onDismiss: () -> Unit = {},
    onSubmitClick: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = {onDismiss()},
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x804C4C4C)),
            contentAlignment = Alignment.BottomCenter
        ) {
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
                    Text(text = "You’ve Shown Interest",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        color = Color.White,
                        fontSize = 30.sp
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Thanks for showing interest in this exclusive\n event.\n One of our Cassanova hosts will contact you soon with the event access details.",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )
                    Text(
                        text = "Please keep an eye on your email or phone for an invitation.",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        color = TextAAColor,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )


                    Spacer(Modifier.height(20.dp))

                    CommonButton(
                        title = "Okay, Got It",
                        fontSize = 18.sp,
                        modifier = Modifier.height(46.dp).padding(horizontal = 20.dp),
                        onClick = {
                            onSubmitClick()
                        })
                    Spacer(Modifier.height(30.dp))

                }

            }

        }
    }
}


@Preview
@Composable
fun EventInterestDialogPreview() {
    MaterialTheme {
        EventInterestDialog()
    }
}