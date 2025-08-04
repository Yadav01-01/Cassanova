package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.input.SettingHeader
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
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
                    .fillMaxWidth().verticalScroll(rememberScrollState())
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

                Spacer(modifier = Modifier.height(10.dp))

                // Food and Beverages Perks Section
                PerkSection(
                    title = "Food and Beverages Perks",
                    perks = listOf(
                        "1 Free bottle of champagne a month (does not role over).",
                        "Exclusive menu items. Access to off menu items.",
                        "10% discount off food only."
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Parking Perks Section
                PerkSection(
                    title = "Parking Perks",
                    perks = listOf(
                        "Free valet (if applicable)."
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Parking Perks Section
                PerkSection(
                    title = "Referral Benefits",
                    perks = listOf(
                        "For each referral, you will receive 25$ gift card (referral has to be a member for 60 days).",
                                "Custom members card."
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Parking Perks Section
                PerkSection(
                    title = "Hookah Perks",
                    perks = listOf(
                        "1 free hookah a month (specially flavors does not apply).",
                        "Build your own Special Flavor that only you can smoke/purchase."
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Parking Perks Section
                PerkSection(
                    title = "Recognition",
                    perks = listOf(
                        "Members will have a custom specific hookah that acknowledge you’re a member.",
                        "Free Entry.",
                        "1 Buddy Pass (1 pass a month)."
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
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            color = Color.White,
            fontSize = 7.sp,
            modifier = Modifier.padding(start = 5.dp ,end = 12.dp, top = 2.dp)
        )

        Text(
            text = text,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.urbanist_regular)),
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
