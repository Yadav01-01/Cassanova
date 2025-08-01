package com.bussiness.cassanova.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.screen.main.home.HomeScreen
import com.bussiness.cassanova.ui.theme.TextWhite


@Composable
fun HeaderComponent( onNotificationClick: () -> Unit,onSettingClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()
        .background(Color.Black)
        ,) {

    Row(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo on the left
        LogoComponent()

        // Icons on the right
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SettingsIcon(onSettingClick = {
                onSettingClick()
            })
            NotificationIcon(onNotificationClick = {
                onNotificationClick()
            })
        }
    }
        Divider(thickness = 2.dp, color = Color.White)

    }
}

@Composable
fun LogoComponent() {
    Image(
        painter = painterResource(id = R.drawable.ic_cassanova_icon),
        contentDescription = "", modifier = Modifier.size(40.dp)
    )
}

@Composable
fun ReverseTableHeader(title: String,onNotificationClick: () -> Unit,onSettingClick: () -> Unit){
    Column(modifier = Modifier.fillMaxWidth()
        .background(Color.Black)
        ,) {


        Row(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo on the left
            //"Reserve a Table"
           Text(text = title, fontSize = 20.sp,
               color = Color.White,
               fontFamily = FontFamily(Font(R.font.urbanist_semibold)))

            // Icons on the right
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SettingsIcon(onSettingClick = {
                    onSettingClick()
                })
                NotificationIcon(onNotificationClick = {
                    onNotificationClick()
                })
            }
        }
        Divider(thickness = 2.dp, color = Color.White)

    }
}


//@Preview(showBackground = true)
//@Composable
//fun ReverseTableHeaderPreview(){
//    MaterialTheme {
//        ReverseTableHeader()
//    }
//
//}


@Composable
fun SettingsIcon(onSettingClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_settings_icon),
        contentDescription = "", modifier = Modifier.size(40.dp).clickable{
            onSettingClick()
        }
    )
}


@Composable
fun NotificationIcon(onNotificationClick: () -> Unit) {
    BadgedBox(
        badge = {
            Badge(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-1).dp, y = (-2).dp),
                containerColor = TextWhite,
            ) {
                val badgeNumber = "2"
                Text(
                    badgeNumber,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    modifier = Modifier.semantics {

                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFC7A65E),
                            Color(0xFFFBE29A),
                            Color(0xFFBE9B43)
                        )
                    ),
                    shape = CircleShape
                )
                .clickable { onNotificationClick()},
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_notifications_outline),
                contentDescription = "Notifications",
                modifier = Modifier.size(22.dp)
            )
        }
    }
}




@Composable
fun SettingHeader(title: String, onBackClick: () -> Unit) {
    Column(modifier = Modifier.background(Color.Black)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 17.dp,
                bottom = 17.dp
            )
        ) {
            Row(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image( painter = painterResource(R.drawable.ic_back_icon_2),
                    contentDescription = "back", modifier = Modifier
                        .clickable { onBackClick() }
                        .wrapContentSize())
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    color = TextWhite
                )
            }



        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color(0xFFD9D9D9)))

    }
}



@Composable
fun SummaryHeader(title: String, onBackClick: () -> Unit,onNotificationClick: () -> Unit,onSettingClick: () -> Unit) {
    Column(modifier = Modifier.background(Color.Black)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding( start = 20.dp,
                    end = 20.dp,
                    top = 17.dp,
                    bottom = 17.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_icon),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )
            }

            Row {
                SettingsIcon(onSettingClick = {
                    onSettingClick()
                })
                Spacer(Modifier.width(10.dp))

                NotificationIcon(onNotificationClick = {
                    onNotificationClick()
                })
            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color(0xFFD9D9D9)))

    }
}





