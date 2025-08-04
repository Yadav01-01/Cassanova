package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.NotificationData
import com.bussiness.cassanova.ui.component.input.SettingHeader
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.viewModel.NotificationViewModel

@Composable
fun NotificationScreen(
    navController: NavHostController
) {
    val viewModel: NotificationViewModel = hiltViewModel()
    val notifications by viewModel.notifications.collectAsState()
    var backPressedTime by remember { mutableStateOf(0L) }
    Column(Modifier.fillMaxSize().background(Color.Black)) {
        SettingHeader(title = "Notifications",onBackClick= {
            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime > 1000) { // 1 second threshold
                backPressedTime = currentTime
                navController.popBackStack()
            }
        })
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),

            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(notifications) { notification ->
                NotificationItem(notification = notification)
            }
        }
    }


}

@Composable
fun NotificationItem(
    notification: NotificationData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image
        AsyncImage(
            model = notification.profileImage,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(41.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_menu_gallery)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = notification.message,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = notification.timestamp,
                color = TextAAColor,
                fontSize = 12.sp
            )
        }
    }

    // Divider
    Divider(
        color = Color.White,
        thickness = 1.dp
    )
}
