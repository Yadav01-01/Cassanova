package com.bussiness.cassanova.ui.component.spinner

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.ui.theme.gradientBrush
import com.bussiness.cassanova.R
/*
@Composable
fun CustomPowerSpinner(
    modifier: Modifier = Modifier,
    selectedText: String = "Select Reason*",
    onSelectionChanged: (String) -> Unit = {},
    menuPadding: Dp = 24.dp,
    reasons : List<String> = listOf<String>()
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedReason by remember { mutableStateOf(selectedText) }

//    val reasons = listOf(
//        "Booking Made by Mistake",
//        "Plans Postponed",
//        "Unable to Reach Venue",
//        "Unexpected Emergency",
//        "Other"
//    )

    Column (modifier = modifier) {
        // Selector Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            border = BorderStroke(1.dp, Color(0xFF4A4A4A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedReason,
                    color = if (selectedReason == "Select Reason*") Color(0xFFB0B0B0) else Color.White,
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFFB0B0B0)
                )
            }
        }

        // Custom dropdown replacement
        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFF4A4A4A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Black)
                        .verticalScroll(rememberScrollState())
                ) {
                    reasons.forEachIndexed { index, reason ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedReason = reason
                                    expanded = false
                                    onSelectionChanged(reason)
                                }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = reason,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                        if (index != reasons.lastIndex) {
                            Divider(
                                color = Color(0xFF4A4A4A),
                                thickness = 1.dp,
                            )
                        }
                    }
                }
            }
        }
    }
}

 */

@Composable
fun CustomPowerSpinner(
    modifier: Modifier = Modifier,
    selectedText: String = "Select Reason*",
    onSelectionChanged: (String) -> Unit = {},
    menuPadding: Dp = 24.dp,
    reasons: List<String> = listOf()
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedReason by remember { mutableStateOf(selectedText) }
    val goldenColor = Color(0xFFFFD700)

    Box(modifier = modifier.background(Color.Black)) {
        // Main button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            border = BorderStroke(1.dp, Color(0xFF4A4A4A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedReason,
                    color = if (selectedReason == "Select Reason*") Color(0xFFB0B0B0) else Color.White,
                    fontSize = 16.sp
                )

                Image(painter = painterResource(if (expanded) R.drawable.arrow_up else R.drawable.arrow_down),
                    contentDescription = "dropdown",)
//                Icon(
//                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
//                    contentDescription = null,
//                   // style = TextStyle(brush = gradientBrush),
//                    tint =   gradientBrush,
//                )
            }
        }

        // Dropdown overlay (not taking space in layout)
        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp) // adjust depending on height of main button
                .align(Alignment.TopStart)
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                border = BorderStroke(1.dp, Color(0xFF4A4A4A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Black)
                        .verticalScroll(rememberScrollState())
                ) {
                    reasons.forEachIndexed { index, reason ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedReason = reason
                                    expanded = false
                                    onSelectionChanged(reason)
                                }
                                .padding(16.dp)
                        ) {
                            if ( reason == selectedReason){
                                Text(
                                    text = reason,
                                    style = TextStyle(brush = gradientBrush),
                                    fontSize = 16.sp
                                )
                            }else{
                                Text(
                                    text = reason,
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }

                        }
                        if (index != reasons.lastIndex) {
                            Divider(
                                color = Color(0xFF4A4A4A),
                                thickness = 1.dp,
                             //   modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}



