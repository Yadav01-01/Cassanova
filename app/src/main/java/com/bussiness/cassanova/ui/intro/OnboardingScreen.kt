package com.bussiness.cassanova.ui.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.OnboardingPage
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.StatusBarPending
import kotlinx.coroutines.launch

val onboardingPages = listOf(
    OnboardingPage(
        imageRes = R.drawable.onboard1, // Replace with actual drawables
        title = "Welcome to Cassanova\n" +
                "Restaurant & Bar",
        description = "Your premium dining experience\n" +
                "starts here."
    ),
    OnboardingPage(
        imageRes = R.drawable.onboard2,
        title = "Quick Reservations, Timeless Experiences",
        description = "Reserve your table, explore the menu & savor every golden moment."
    ),
    OnboardingPage(
        imageRes = R.drawable.onboard3,
        title = "Experience Taste,\n" +
                "Elegance & Atmosphere",
        description = "Join us at Cassanova for signature flavors and timeless ambiance."
    )
)


@Composable
fun OnboardingScreen(navController: NavHostController, onFinish: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { onboardingPages.size })
    val scope = rememberCoroutineScope()

    Column(Modifier
        .fillMaxSize()) {

        StatusBarPending()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnboardingPageContent(
                page = onboardingPages[page],
                onSkip = {onFinish()},
                onNext = {
                    if (page < onboardingPages.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(page + 1)
                        }
                    } else {
                        navController.navigate(Routes.PHONE_NUMBER_LOGIN_SCREEN)
                    }
                },
                onBack = {
                    if (page >= 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(page - 1)
                        }
                    } else {
                        onFinish()
                    }
                }

            )
        }

        // Page indicators
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 52.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(onboardingPages.size) { index ->
                Box(
                    modifier = Modifier
                        .size(
                            width = if (index == pagerState.currentPage) 24.dp else 8.dp,
                            height = 8.dp
                        )
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            if (index == pagerState.currentPage)
                                Color.White
                            else
                                Color(0xFF808080)
                        )
                )
            }
        }

        // Skip button
        Text(
            text = "Skip",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 52.dp)
                .clickable { onFinish() }
        )
    }
}
}

@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    onSkip: () -> Unit,
    onNext: () -> Unit,
    onBack:() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background image
        Box(  modifier = Modifier
            .fillMaxHeight(0.59f).clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))){
            Image(
                painter = painterResource(id = page.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (page != onboardingPages[0]) { // Or use pageIndex if you have access to it
                Image(
                    painter = painterResource(id = R.drawable.ic_back_icon),
                    contentDescription = "back icon",
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.TopStart)
                        .padding(20.dp)
                        .clickable {  onBack() }
                )
            }
        }


        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f),
                            Color.Black.copy(alpha = 0.9f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(27.dp))

            // Bottom content
            Column {
                Text(
                    text = page.title,
                    color = Color.White,
                    fontSize = 30.sp,
                    lineHeight = 35.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                Text(
                    text = page.description,
                    color = Color(0xFFAAAAAA),
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    modifier = Modifier.padding(bottom = 32.dp)
                )


                    Box(
                        modifier = Modifier
                            .fillMaxWidth().height(52.dp).clickable{
                                onNext()
                            }
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFC7A65E),
                                        Color(0xFFFBE29A),
                                        Color(0xFFBE9B43)
                                    )
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Let's Start",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        )
                    }


                Spacer(modifier = Modifier.height(76.dp))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    OnboardingScreen(
        navController = navController,
        onFinish = { /* No-op for preview */
        }
    )
}


