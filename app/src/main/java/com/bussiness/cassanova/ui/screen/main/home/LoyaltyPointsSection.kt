package com.bussiness.cassanova.ui.screen.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.input.CommonArrowButton
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.TextWhite
import com.bussiness.cassanova.ui.theme.gradientBrush
@Composable
fun LoyaltyPointsSection(points: Int,navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth().height(186.dp).padding(horizontal = 20.dp)
            .border(1.dp, color = TextAAColor, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.icons_images),
                contentDescription = "Achievement Badge",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4674f)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterEnd),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
                Text(
                    text = "Congratulations",
                    color = TextWhite,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Currently You've unlocked",
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = points.toString(),
                        fontSize = 40.sp,
                        style = TextStyle(brush = gradientBrush),
                        fontFamily = FontFamily(Font(R.font.urbanist_bold))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "loyalty points.",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                CommonArrowButton(
                    title = "Check Now",
                    modifier = Modifier.height(36.dp).width(151.dp),
                    onClick = {
                        navController.navigate(Routes.POINTS_SCREEN)

                        showDialog = true
                              },
                    fontSize = 16.sp
                )
            }
        }
    }


}
