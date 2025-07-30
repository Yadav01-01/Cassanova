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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bussiness.cassanova.ui.component.CommonButton

@Composable
fun CongratulationsDialog(
    onDismiss: () -> Unit = {},
    onClick : () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true,

        ),


    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x804C4C4C)),
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
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Icon (you can replace with your actual icon resource)


                    Image(
                        painter = painterResource(id = R.drawable.ic_icons_party_icon),
                        contentDescription = "Party Icon",
                        modifier = Modifier
                            .wrapContentSize()

                    )

                    // Title
                    Text(
                        text = "Congratulations!",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    // Description
                    Text(
                        text = "Everything’s set. Discover elegance, energy, and extraordinary flavors.",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        color = Color.White,
                        lineHeight = 22.sp
                    )


                    CommonButton(
                        onClick = { onClick() },
                        title = "Start Exploring",
                        fontSize = 18.sp,
                        modifier = Modifier.width(161.dp).height(46.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun CongratulationsDialogPreview() {
    CongratulationsDialog()
}