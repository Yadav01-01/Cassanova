package com.bussiness.cassanova.ui.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.input.CommonButton
import com.bussiness.cassanova.ui.theme.TextAAColor

@Composable
fun EventsDialog(
    onDismiss: () -> Unit = {},
    onSubmitClick: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = onDismiss,
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
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
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9269f).clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                    .align(Alignment.BottomCenter)
                    .background(Color.Black).verticalScroll(rememberScrollState()),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),

                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_party_image),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(20.dp)

                ) {


                    Text(
                        text = "VIP Lounge Access",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        fontSize = 24.sp,
                        color = Color.White
                    )

                    Text(
                        text = "Saturday, 27 May – 10:00 AM",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        fontSize = 16.sp,
                        color = TextAAColor
                    )

                    Text(
                        text = "From wine tastings to rooftop sessions — only for our elite guests. Cassanova isn't just a place, it’s a privilege.\u2028Our members step into a world where luxury meets exclusivity.\n" +
                                "\n" +
                                "Enjoy curated evenings of live jazz, intimate chef's tables, and seasonal cocktail unveilings. Whether it's a velvet lounge takeover or private rooftop sundowners, you’re always on the list.\n" +
                                "\n" +
                                "We design experiences that blend refined taste with unforgettable ambiance. Expect early access to new menus, invite-only events, and priority reservations.\u2028 Every detail, from the lighting to the music, is crafted for indulgence. Toast under the stars, mingle with fellow connoisseurs, or simply unwind in your own reserved space.\u2028\u2028Our loyalty program rewards not just your visits, but your lifestyle.\u2028\u2028LINK - https://event.stream123.net/registration/go/lkewiow91TIB4",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    Spacer(Modifier.height(10.dp))

                    CommonButton(
                        title = "Join the Guest List",
                        fontSize = 18.sp,
                        modifier = Modifier.height(46.dp),
                        onClick = {
                            onSubmitClick()
                        })
                    Spacer(Modifier.height(30.dp))
                }


            }

        }

    }
    }
}

@Preview
@Composable
fun EventsDialogPreview() {
    MaterialTheme {
        EventsDialog()
    }
}