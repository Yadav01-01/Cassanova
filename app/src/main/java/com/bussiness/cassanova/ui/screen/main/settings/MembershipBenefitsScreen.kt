package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.SettingHeader
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun MembershipBenefitsScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SettingHeader(title = "Membership Benefits", onBackClick = {
            navController.popBackStack()
        })

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                // Exclusive Access Section
                PerkSection(
                    title = "Exclusive Access",
                    perks = listOf(
                        "Priority Reservations.",
                        "Skip the line entry.",
                        "Members only Event. Invitations to private parties and tastings."
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Food and Beverages Perks Section
                PerkSection(
                    title = "Food and Beverages Perks",
                    perks = listOf(
                        "1 Free bottle of champagne a month (does not role over).",
                        "Exclusive menu items. Access to off menu items.",
                        "10% discount off food only."
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Parking Perks Section
                PerkSection(
                    title = "Parking Perks",
                    perks = listOf(
                        "Free valet (if applicable)."
                    )
                )
            }



    }
}


@Composable
fun PerkSection(
    title: String,
    perks: List<String>
) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            style = TextStyle(
                brush = gradientBrush
            ),
          fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        perks.forEach { perk ->
            PerkItem(text = perk)
        }
    }
}

@Composable
fun PerkItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 12.dp, top = 2.dp)
        )

        Text(
            text = text,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
            fontSize = 14.sp,
            lineHeight = 22.sp,
            modifier = Modifier.weight(1f)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun MembershipBenefitsScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        MembershipBenefitsScreen(navController = navController)
    }
}
