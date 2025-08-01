package com.bussiness.cassanova.ui.screen.main.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.MenuListData
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.CommonUnlockButton
import com.bussiness.cassanova.ui.component.DashedDivider
import com.bussiness.cassanova.ui.component.ReverseTableHeader
import com.bussiness.cassanova.ui.component.SummaryHeader
import com.bussiness.cassanova.ui.component.dialog.FilterDialog
import com.bussiness.cassanova.ui.theme.TextAAColor
import com.bussiness.cassanova.ui.theme.gradientBrush
import com.bussiness.cassanova.viewModel.MenuViewModel

@Composable
fun MenuScreen(navController: NavHostController) {
    val viewModel: MenuViewModel = viewModel()
    val menuItems by viewModel.filteredMenuItems.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {

        ReverseTableHeader(title = "Menu",onNotificationClick ={navController.navigate(Routes.NOTIFICATION_SCREEN)},
            onSettingClick = {
                navController.navigate(Routes.SETTING_SCREEN)
            })


        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                OutlinedTextField(
                    value = searchText,
                    onValueChange = { viewModel.updateSearchText(it) },
                    placeholder = {
                        Text(
                            "Start typing to find a dish...",
                            color = Color(0xFFD9D9D9),
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            fontSize = 14.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    },
                    modifier = Modifier
                        .weight(1f),


                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color(0xFFD9D9D9),
                        cursorColor = Color.White

                    ),

                    shape = RoundedCornerShape(8.dp)

                )

                Spacer(Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_filter_icon),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            showDialog = true

                        })
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                // verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(menuItems) { item ->
                    MenuItemCardDesign(item = item)
                }
            }
        }
    }

    if (showDialog){
        FilterDialog(onDismiss={
            showDialog = false
        },onSubmitClick={
            showDialog = false
        })
    }

}


@Composable
fun MenuItemCardDesign(item: MenuListData) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {


        Column(
            modifier = Modifier
                .then(
                    if (item.isLocked) {
                        Modifier.blur(radius = 5.dp)
                    } else {
                        Modifier
                    }
                )
        )  {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left column (text content)
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {


                Text(
                    text = item.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = item.category,
                    color = TextAAColor,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.price,
                    style = TextStyle(
                        brush = gradientBrush
                    ),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),

                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.description,
                    color = TextAAColor,
                    fontSize = 14.sp,
                    maxLines = 3,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    overflow = TextOverflow.Ellipsis,
                )
            }

            // Image box
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = item.imageRes,
                    contentDescription = item.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )


            }


        }

            DashedDivider(
                color = Color(0xFFBD9A42),
                thickness = 1.dp,
                dashLength = 10.dp,
                gapLength = 5.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

        }
        if (item.isLocked) {

            Box(
                modifier = Modifier
                    .matchParentSize()
                   // .blur(radius = 8.dp)
                    .background(Color.Black.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                CommonUnlockButton(title = "Unlock with Membership", modifier = Modifier.wrapContentSize().padding(horizontal = 10.dp, vertical = 10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    val navController = rememberNavController()
    MenuScreen(navController = navController)
}