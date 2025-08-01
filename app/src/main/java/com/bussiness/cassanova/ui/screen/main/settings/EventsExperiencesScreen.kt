package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.EventCarouselData
import com.bussiness.cassanova.ui.component.SettingHeader
import com.bussiness.cassanova.ui.component.dialog.EventInterestDialog
import com.bussiness.cassanova.ui.component.dialog.EventsDialog
import com.bussiness.cassanova.ui.screen.main.home.EventsCarousel
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun EventsExperiencesScreen(navController: NavHostController) {
    var backPressedTime by remember { mutableStateOf(0L) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SettingHeader(title = "Events & Experiences", onBackClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime > 1000) { // 1 second threshold
                backPressedTime = currentTime
                navController.popBackStack()
            }
        })


        val sampleEvents = listOf(
            EventCarouselData(
                title = "VIP Lounge Access",
                date = "Saturday, 27 May - 10:00 AM",
                description = "From wine tastings to rooftop sessions — only for our elite guests.",
                imageRes = R.drawable.dummy_social_media_post // Replace with actual resource
            ),
            EventCarouselData(
                title = "Exclusive Dining",
                date = "Sunday, 28 May - 7:00 PM",
                description = "Curated culinary experiences with world-renowned chefs.",
                imageRes = R.drawable.ic_party_image // Replace with actual resource
            ),
            EventCarouselData(
                title = "Private Concert",
                date = "Monday, 29 May - 8:30 PM",
                description = "Intimate musical performances in our exclusive venue.",
                imageRes = R.drawable.ic_party_image// Replace with actual resource
            )
        )

        Spacer(Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),

            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(sampleEvents) { sampleEvents ->
                EventCard(event = sampleEvents)
            }
        }

    }

}


@Composable
fun EventCard(
    event: EventCarouselData,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var showInterestDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {


        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(295.dp)
                .border(width = 1.dp, color = Color(0xFF191919), shape = RoundedCornerShape(8.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF191919))
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp)),

                    ) {
                    AsyncImage(
                        model = event.imageRes,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 7.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = event.title,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = event.date,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = event.description,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        color = TextAAColor,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = event.buttonText,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        style = TextStyle(
                            brush = gradientBrush
                        ), // Orange color
                        modifier = Modifier.clickable {
                            showDialog = true
                        }
                    )
                }
            }
        }
    }
    if (showDialog) {
        EventsDialog(onDismiss = { showDialog = false }, onSubmitClick = { showDialog = false
            showInterestDialog= true })
    }

    if (showInterestDialog){
        EventInterestDialog(onDismiss = { showInterestDialog = false }, onSubmitClick = { showInterestDialog = false
        })
    }
}