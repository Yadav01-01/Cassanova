package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.ExpandableItem
import com.bussiness.cassanova.ui.component.SettingHeader
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun FaqScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SettingHeader(title = "FAQ's", onBackClick = {
            navController.popBackStack()
        })

        var items by remember {
            mutableStateOf(
                listOf(
                    ExpandableItem(
                        id = 1,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.",
                        isExpanded = true
                    ),
                    ExpandableItem(
                        id = 2,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    ),
                    ExpandableItem(
                        id = 3,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    ),
                    ExpandableItem(
                        id = 4,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    ),
                    ExpandableItem(
                        id = 5,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    ),
                    ExpandableItem(
                        id = 6,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    ),
                    ExpandableItem(
                        id = 6,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    ),
                    ExpandableItem(
                        id = 7,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    ),
                    ExpandableItem(
                        id = 8,
                        title = "Lorem Ipsum is simply dummy text.",
                        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
                    )
                )
            )
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                ExpandableCard(
                    item = item,
                    onToggle = { toggledItem ->
                        items = items.map { currentItem ->
                            if (currentItem.id == toggledItem.id) {
                                currentItem.copy(isExpanded = !currentItem.isExpanded)
                            } else {
                                currentItem
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ExpandableCard(
    item: ExpandableItem,
    onToggle: (ExpandableItem) -> Unit
) {
    Column {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(
                    brush = gradientBrush
                )
        ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()

                    .clickable { onToggle(item) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = if (item.isExpanded) {
                       painterResource(R.drawable.ic_show_drop_down_icon)
                    } else {
                        painterResource(R.drawable.ic_hide_drop_down_icon)
                    },
                    contentDescription = if (item.isExpanded) "Collapse" else "Expand",
                    tint = Color.Black
                )
            }

            // Expandable content

        }
    }

        Spacer(Modifier.height(10.dp))
        AnimatedVisibility(
            visible = item.isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically(),
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFF333333)).padding(vertical = 5.dp))
        {
            Text(
                text = item.content,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp )
            )
        }
Spacer(Modifier.height(10.dp))
}
}

@Preview(showBackground = true)
@Composable
fun FaqScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        FaqScreen(navController = navController)
    }
}