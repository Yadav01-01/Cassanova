package com.bussiness.cassanova.ui.component.sheet

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.CommonWhiteBorderButton
import com.bussiness.cassanova.ui.component.spinner.CustomPowerSpinner


@Composable
fun CancellationReasonSheet(
    selectedReason: String,
    description: String,
    onReasonChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDismiss: () -> Unit = {},
    onSubmitClick: () -> Unit = {},
    reasons : List<String> = listOf<String>()
) {
    var selectedReason by remember { mutableStateOf("Select Reason*") }

    var expanded by remember { mutableStateOf(false) }

//    val reasons = listOf(
//        "Booking Made by Mistake",
//        "Plans Postponed",
//        "Unable to Reach Venue",
//        "Unexpected Emergency",
//        "Other"
//    )


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

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .background(
                            Color.Black,
                            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                        )
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.cancel_ic),
                        contentDescription = "icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                    // Title
                    Text(
                        text = "Cancellation Reason",
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Dropdown
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .border(1.dp, Color.White, RoundedCornerShape(8.dp))
//                            .clip(RoundedCornerShape(8.dp))
//                            .background(Color.Black)
//                            .clickable { expanded = !expanded }
//                            .padding(horizontal = 12.dp, vertical = 14.dp)
//                    ) {
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                text = if (selectedReason.isEmpty()) "Select Reason*" else selectedReason,
//                                color = Color.White,
//                                fontSize = 14.sp
//                            )
//                            Icon(
//                                painter = painterResource(id = if (expanded) R.drawable.arrow_up else R.drawable.arrow_down),
//                                contentDescription = "Expand",
//                                tint = Color.Unspecified
//                            )
//                        }
//
//                        DropdownMenu(
//                            expanded = expanded,
//                            onDismissRequest = { expanded = false },
//                            modifier = Modifier
//                                .background(Color.Black)
//                                .fillMaxWidth().padding(horizontal = 12.dp)
//                                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
//                        ) {
//                            reasons.forEachIndexed { index, reason ->
//                                DropdownMenuItem(
//                                    text = {
//                                        Text(
//                                            text = reason,
//                                            color = Color.White,
//                                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
//                                            fontSize = 14.sp
//                                        )
//                                    },
//                                    onClick = {
//                                        onReasonChange(reason)
//                                        expanded = false
//                                    }
//                                )
//                                // Show divider only if it's not the last item
//                                if (index < reasons.lastIndex) {
//                                    HorizontalDivider(thickness = 1.dp, color = Color.White)
//                                }
//                            }
//                        }
//
//                    }

                    CustomPowerSpinner(
                        selectedText = selectedReason,
                        onSelectionChanged = { reason ->
                            selectedReason = reason
                        },
                        menuPadding = 24.dp,
                        reasons = reasons
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Description field
                    OutlinedTextField(
                        value = description,
                        onValueChange = onDescriptionChange,
                        placeholder = {
                            Text("Describe Your Reason", color = Color.White)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF808080),
                            unfocusedBorderColor = Color(0xFF808080),
                            textColor = Color.White,
                            cursorColor = Color(0xFF808080)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        maxLines = 5
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CommonWhiteBorderButton(
                            title = "Close",
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            onClick = {onDismiss()}
                        )
                        CommonButton(
                            title = "Submit",
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            onClick = {onSubmitClick()}
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }

            }

}
