package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.SettingItem
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.input.BottomSheetBehaviorProperties
import com.bussiness.cassanova.ui.component.input.CustomSwitch
import com.bussiness.cassanova.ui.component.input.SettingHeader
import com.bussiness.cassanova.ui.component.dialog.DeleteAccountDialog
import com.bussiness.cassanova.ui.component.dialog.LogOutDialog
import com.bussiness.cassanova.ui.theme.TextAAColor


//@OptIn(ExperimentalMaterial3Api::class)
private enum class DarkIconsValue {
    Default, True, False
}

@Composable
fun SettingScreen(authNavController: NavController, navController: NavHostController) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showLogOutDialog by remember { mutableStateOf(false) }
    var backPressedTime by remember { mutableStateOf(0L) }
    val surfaceColor = androidx.compose.material.MaterialTheme.colors.surface
    var navigationBarColor by remember(surfaceColor) {
        mutableStateOf(surfaceColor)
    }
    var darkIcons by remember {
        mutableStateOf(DarkIconsValue.Default)
    }
    var state by remember {
        mutableStateOf(BottomSheetBehaviorProperties.State.Collapsed)
    }
    var maxWidth by remember {
        mutableStateOf(BottomSheetBehaviorProperties.Size.NotSet)
    }
    var maxHeight by remember {
        mutableStateOf(BottomSheetBehaviorProperties.Size.NotSet)
    }
    var isDraggable by remember {
        mutableStateOf(true)
    }
    var expandedOffset by remember {
        mutableStateOf(0)
    }
    var halfExpandedRatio by remember {
        mutableStateOf(0.5F)
    }
    var isHideable by remember {
        mutableStateOf(true)
    }
    var peekHeight by remember {
        mutableStateOf(BottomSheetBehaviorProperties.PeekHeight.Auto)
    }
    var isFitToContents by remember {
        mutableStateOf(true)
    }
    var skipCollapsed by remember {
        mutableStateOf(false)
    }
    var isGestureInsetBottomIgnored by remember {
        mutableStateOf(false)
    }
//    val sheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = true,
//        confirmValueChange = { it != SheetValue.PartiallyExpanded }
//    )

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SettingHeader(title = "Settings", onBackClick = {


            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime > 1000) { // 1 second threshold
                backPressedTime = currentTime
                navController.popBackStack()
            }
           // navController.popBackStack()
        })

        val menuItems = listOf(
            SettingItem(
                R.drawable.setting_icon_notifications,
                "Notifications",
                hasToggle = true,
                isToggleEnabled = notificationsEnabled
            ),
            SettingItem(R.drawable.setting_icon_event, "Events & Experiences"),
            SettingItem(R.drawable.setting_icon_membership, "Membership Benefits"),
            SettingItem(R.drawable.setting_icon_faq, "FAQs"),
            SettingItem(R.drawable.setting_icon_contact, "Contact Us"),
            SettingItem(R.drawable.setting_icon_about, "About Us"),
            SettingItem(R.drawable.setting_icon__term, "Terms & Conditions"),
            SettingItem(R.drawable.setting_icon__term, "Privacy Policy"),
            SettingItem(R.drawable.setting_icon_delete, "Delete Account"),
            SettingItem(R.drawable.setting_icon_logout, "Logout")
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            // Profile Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Avatar
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {

                    AsyncImage(
                        model = R.drawable.empty_image,
                        contentDescription = "Profile",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.empty_image),
                        error = painterResource(id = R.drawable.empty_image)
                    )

                }

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Sarah Jonson",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),

                        )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "+1 (XXX) XXX-XXXX",
                        color = TextAAColor,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        fontSize = 14.sp
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_edit_icon),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            navController.navigate(Routes.PROFILE_SCREEN)
                        }
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            Divider(thickness = 1.dp, color = Color(0xFF808080))
            // Menu Items
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(menuItems) { item ->
                    SettingItemRow(
                        item = item,
                        onToggleChange = { isEnabled ->
                            if (item.title == "Notifications") {
                                notificationsEnabled = isEnabled
                            }
                        },
                        onClick = {
                            when (item.title) {
                                "Events & Experiences" -> {
                                    navController.navigate(Routes.EVENTS_EXPERIENCES_SCREEN)
                                }

                                "FAQs" -> {
                                    navController.navigate(Routes.FAQ_SCREEN)
                                }

                                "About Us" -> {
                                    navController.navigate(Routes.ABOUT_US_SCREEN)
                                }

                                "Terms & Conditions" -> {
                                    navController.navigate(Routes.TERM_SCREEN)
                                }

                                "Privacy Policy" -> {
                                    navController.navigate(Routes.PRIVACY_SCREEN)
                                }

                                "Contact Us" -> {
                                    navController.navigate(Routes.CONTACT_US_SCREEN)
                                }

                                "Membership Benefits" -> {
                                    navController.navigate(Routes.MEMBERSHIP_BENEFITS_SCREEN)
                                }

                                "Delete Account" -> {
                                    showDeleteDialog = true
                                }

                                "Logout" -> {
                                    showLogOutDialog = true
                                }

                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
        }

    }


    if (showDeleteDialog) {
        DeleteAccountDialog(onDismiss = { showDeleteDialog = false }, onDeleteClick = {
            showDeleteDialog = false

            authNavController.navigate(Routes.PHONE_NUMBER_LOGIN_SCREEN) {
                // Clear the back stack so user can't navigate back
                popUpTo(Routes.MAIN_SCREEN) { inclusive = true }
            }
        }, onCancelClick = {
            showDeleteDialog = false

        })

    }



    if (showLogOutDialog) {
        LogOutDialog(onDismiss = { showLogOutDialog = false }, onLogOutClick = {
            showLogOutDialog = false
            authNavController.navigate(Routes.PHONE_NUMBER_LOGIN_SCREEN) {
                // Clear the back stack so user can't navigate back
                popUpTo(Routes.MAIN_SCREEN) { inclusive = true }
            }
        }, onCancelClick = {
            showLogOutDialog = false
        })
    }

}


@Composable
fun SettingItemRow(
    item: SettingItem,
    onToggleChange: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {
    Column {
        Spacer(Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(item.icon),
                contentDescription = item.title,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = item.title,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                modifier = Modifier.weight(1f)
            )

            if (item.hasToggle) {

                CustomSwitch(
                    checked = item.isToggleEnabled,
                    onCheckedChange = onToggleChange,
                    width = 36.dp,
                    height = 20.dp,
                    thumbSize = 12.dp
                )
            }
        }
        Spacer(Modifier.height(5.dp))

        if (item.title != "Logout") {
            Divider(thickness = 1.dp, color = Color(0xFF808080))
        }

    }

}


@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    val authNavController = rememberNavController()
    val navController = rememberNavController()
    MaterialTheme {
        SettingScreen(authNavController, navController)
    }
}