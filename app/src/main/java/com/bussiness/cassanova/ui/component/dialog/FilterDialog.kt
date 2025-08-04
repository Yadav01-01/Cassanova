package com.bussiness.cassanova.ui.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.CommonWhiteBorderButton
import com.bussiness.cassanova.ui.theme.gradientBrush
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun FilterDialog(
    onDismiss: () -> Unit = {},
    onSubmitClick: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        var selectedCategories by remember { mutableStateOf(setOf<String>()) }
        var selectedDietary by remember { mutableStateOf(setOf<String>()) }

         Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x804C4C4C)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cross_icon),
                        contentDescription = "Close",
                        modifier = Modifier
                            .clickable {
                                onDismiss()
                            }
                            .size(40.dp)
                            .clip(CircleShape)

                    )

                    Spacer(Modifier.height(20.dp))



                // Main dialog content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .background(
                            Color.Black,
                            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                        )
                        .padding(horizontal = 21.dp, vertical = 30.dp)
                ) {
                    // Title
                    Text(
                        text = "Filters",
                        style = TextStyle(
                            brush = gradientBrush
                        ),
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
                    )

                    // Categories Section
                    Text(
                        text = "Categories",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    val categories =
                        listOf("Appetizers", "Main Course", "Drinks", "Desserts", "Specials")

                    FlowRow(
                        mainAxisSpacing = 12.dp,
                        crossAxisSpacing = 12.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp)
                    ) {
                        categories.forEach { category ->
                            FilterChip(
                                text = category,
                                isSelected = selectedCategories.contains(category),
                                onClick = {
                                    selectedCategories =
                                        if (selectedCategories.contains(category)) {
                                            selectedCategories - category
                                        } else {
                                            selectedCategories + category
                                        }
                                }
                            )
                        }
                    }

                    // Dietary Section
                    Text(
                        text = "Dietary",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    val dietaryOptions =
                        listOf("Gluten-Free", "Vegan", "Plant-Based", "Eggless", "Vegetarian")
                    FlowRow(
                        mainAxisSpacing = 12.dp,
                        crossAxisSpacing = 12.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp)
                    ) {
                        dietaryOptions.forEach { option ->
                            FilterChip(
                                text = option,
                                isSelected = selectedDietary.contains(option),
                                onClick = {
                                    selectedDietary = if (selectedDietary.contains(option)) {
                                        selectedDietary - option
                                    } else {
                                        selectedDietary + option
                                    }
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CommonWhiteBorderButton(
                            title = "Clear",
                            modifier = Modifier.weight(1f).height(46.dp),
                            onClick = {
                                selectedCategories = emptySet()
                                selectedDietary = emptySet()
                            }
                        )

                        CommonButton(
                            title = "Apply",
                            modifier = Modifier.weight(1f).height(46.dp),
                            onClick = onSubmitClick
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
            }
       // }
    }
}




@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Box( modifier = Modifier.wrapContentSize().clickable{onClick()}.then(when{
        isSelected ->  Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFC7A65E),
                    Color(0xFFFBE29A),
                    Color(0xFFBE9B43)
                )
            ),
            shape = RoundedCornerShape(10.dp)
        )
        else -> Modifier.border(1.dp, Color.White, RoundedCornerShape(10.dp))

    }).padding(horizontal = 10.dp, vertical = 10.dp),
        ){
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color.White,
            fontSize = 15.sp,

            fontFamily = FontFamily(Font(R.font.urbanist_medium))
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FilterDialogPreview() {
    FilterDialog()
}