package com.bussiness.cassanova.ui.screen.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.EventCarouselData
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.dialog.EventInterestDialog
import com.bussiness.cassanova.ui.component.dialog.EventsDialog
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.gradientBrush
import com.bussiness.cassanova.viewModel.HomeViewModel


@Composable
fun EventItem(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()){
    val events by viewModel.events.collectAsState()

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
            imageRes = R.drawable.dummy_social_media_post // Replace with actual resource
        ),
        EventCarouselData(
            title = "Private Concert",
            date = "Monday, 29 May - 8:30 PM",
            description = "Intimate musical performances in our exclusive venue.",
            imageRes = R.drawable.dummy_social_media_post// Replace with actual resource
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
        // Dark background like in the image
    ) {
        EventsCarousel(
            navController= navController,
            events = events,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun EventsCarousel(
    navController : NavHostController,
    events: List<EventCarouselData>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { events.size })

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth() .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Events & Experiences",
                fontSize = 20.sp,
                style = TextStyle(
                    brush = gradientBrush
                ),
                fontFamily = FontFamily(Font(R.font.urbanist_semibold))
            )

            Image(painter = painterResource(id = R.drawable.next_arrow_button),
                contentDescription = "View all",
                modifier = Modifier.size(25.dp).clickable{navController.navigate(Routes.EVENTS_EXPERIENCES_SCREEN)}
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        // ViewPager with side peek
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp
        ) { page ->
            EventCard(
                event = events[page],
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Page Indicators
        PageIndicators(
            pageCount = events.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun EventCard(
    event: EventCarouselData,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(285.dp)
            .border(width = 1.dp, color = Color(0xFF191919), shape = RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFF191919))
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp).clip(RoundedCornerShape(8.dp)),

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
                    modifier= Modifier.clickable{
                        showDialog = true
                    }
                )
            }
        }
    }
    if (showDialog){
        EventsDialog( onDismiss = { showDialog = false }, onSubmitClick = {
            showDialog = false
            showDialog2 = true })
    }

    if (showDialog2) {
        EventInterestDialog(
            onSubmitClick = { showDialog2 = false },
            onDismiss = { showDialog2 = false }
        )
    }
}

@Composable
fun PageIndicators(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { page ->
            Box(
                modifier = Modifier
                    .size(
                        width = if (page == currentPage) 24.dp else 8.dp,
                        height = 8.dp
                    )
                    .background(

                        color = if (page == currentPage) {
                            Color(0xFFD4B563) // Orange for active
                        } else {
                            Color(0xFF808080) // Semi-transparent for inactive
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}
