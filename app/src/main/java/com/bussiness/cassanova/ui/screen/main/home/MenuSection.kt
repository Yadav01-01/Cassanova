package com.bussiness.cassanova.ui.screen.main.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.MenuItem
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.dialog.DeleteAccountDialog
import com.bussiness.cassanova.ui.component.dialog.MenuItemDetailDialog
import com.bussiness.cassanova.ui.theme.DarkCard
import com.bussiness.cassanova.ui.theme.GoldColor
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun MenuSection(navController: NavHostController,menuItems: List<MenuItem>) {
    Column(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Discover the Menu",
                    color = Color.White,
                    fontSize = 20.sp,
                    style = TextStyle(
                        brush = gradientBrush
                    ),
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )

                Image(
                    painter = painterResource(id = R.drawable.next_arrow_button),
                    contentDescription = "View all",
                    modifier = Modifier.size(25.dp).clickable {
navController.navigate(Routes.MENU_SCREEN)
                    })
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
               // pageSpacing = 16.dp
            ) {
                items(menuItems) { item ->
                    MenuItemCard(item)
                }
            }

        }



    }
}
@Composable
fun MenuItemCard(item: MenuItem) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .width(135.dp)
            .height(210.dp).clickable{
                showDialog = true

            },
        colors = CardDefaults.cardColors(
            containerColor = DarkCard
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),

                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id= item.imageRes),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = item.name,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        maxLines = 1
                    )
                    Text(
                        text = item.category,
                        color = TextAAColor,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = item.price,
                        style = TextStyle(
                            brush = gradientBrush
                        ),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold))
                    )
                }


            }
        }
    }
if (showDialog){
  //  DeleteAccountDialog(onDismiss = {showDialog = false}, onDeleteClick = {showDialog = false })
    MenuItemDetailDialog(onDismiss = {showDialog = false}, onSubmitClick = {showDialog = false })
}

}