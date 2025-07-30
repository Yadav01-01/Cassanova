package com.bussiness.cassanova.ui.screen.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.theme.TextWhite
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun UserGreetingSection() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            // In real app, use AsyncImage or similar for profile picture
            Image(
                painter = painterResource(id= R.drawable.dummy_social_media_post),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "Good Evening,",
                color = TextWhite,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                fontSize = 18.sp
            )

            Text(
                text = "Sarah Jonson !",
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                style = TextStyle(
                    brush = gradientBrush
                )
            )
            Text(
                text = "Your Cassanova journey continues...",
                color = TextWhite,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                fontSize = 16.sp
            )
        }
    }
}