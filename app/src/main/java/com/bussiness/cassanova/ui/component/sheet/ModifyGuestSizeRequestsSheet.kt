package com.bussiness.cassanova.ui.component.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.SpecialRequest
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.CommonWhiteBorderButton
import com.bussiness.cassanova.ui.screen.main.reverse.RequestChip
import com.bussiness.cassanova.ui.theme.gradientBrush

@Composable
fun ModifyGuestSizeRequestsSheet(onDismiss: () -> Unit = {},
                                 onSubmitClick: () -> Unit = {},){
    var guestCount by remember { mutableIntStateOf(1) }
    var selectedSpecialRequest by remember { mutableStateOf("") }
    var customRequest by remember { mutableStateOf("") }
    var showCustomInput by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val specialRequests = listOf(
        SpecialRequest("Birthday Party", R.drawable.ic_park_solid_birthday_cake_icon),
        SpecialRequest("Anniversary", R.drawable.ic_anniversary_icon),
        SpecialRequest("Baby Chair", R.drawable.ic_baby_fill_icon),
        SpecialRequest("Other", null)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize().imePadding(),
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
                ).verticalScroll(scrollState)
                .padding(horizontal = 21.dp, vertical = 30.dp)
        ) {
            // Title
            Text(
                text = "Modify Guest Size & Requests",
                style = TextStyle(
                    brush = gradientBrush
                ),
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Guest counter section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "How many guests?",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    modifier = Modifier.weight(1f)
                )

                Row(modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly) {

                    Image(painter = painterResource(R.drawable.ic_minuse_button),
                        contentDescription = "Decrease", modifier = Modifier
                            .size(40.dp).clickable{
                                if (guestCount > 1) guestCount--
                            },
                    )

                    Text(
                        text = guestCount.toString(),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .border(width = (0.5).dp, color = Color.White, shape = RoundedCornerShape(5.dp))
                            .padding(horizontal = 25.dp, vertical = 10.dp),
                        textAlign = TextAlign.Center
                    )

                    // Increase button
                    Image(painter = painterResource(R.drawable.ic_plus_button),
                        contentDescription = "Decrease", modifier = Modifier
                            .size(40.dp).clickable{
                                guestCount++
                            },
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Special Requests Section
            Text(
                text = "Any special requests?",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Special request chips - First row
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                specialRequests.take(4).forEach { request ->
                    RequestChip(
                        request = request,
                        isSelected = selectedSpecialRequest == request.name,

                        onClick = {
                            selectedSpecialRequest = if (selectedSpecialRequest == request.name) "" else request.name
                            showCustomInput = request.name == "Other"
                        }
                        ,
                    )
                }
            }




            // Custom request input
            if (showCustomInput) {
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = customRequest,
                    onValueChange = { customRequest = it },
                    shape =  RoundedCornerShape(10.dp),
                    placeholder = { Text("Please describe your request", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    // .border(width = 1.dp , color = Color(0xFF808080), shape =  RoundedCornerShape(10.dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color(0xFFAAAAAA),
                        focusedBorderColor = Color(0xFF808080),
                        unfocusedBorderColor = Color(0xFF808080)
                    ),
                    singleLine = false,
                    minLines = 3,
//                            keyboardOptions = KeyboardOptions(
//                                keyboardType = KeyboardType.Text,  // This sets the email keyboard type
//                                autoCorrect = false,
//                            ),
                    keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text,  // This sets the email keyboard typ
                    ),
//
                )
            }
            Spacer(Modifier.height(30.dp))



            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CommonWhiteBorderButton(
                    title = "Cancel",
                    modifier = Modifier.weight(1f).height(46.dp),
                    onClick = {
                        onDismiss()
                    }
                )

                CommonButton(
                    title = "Update",
                    modifier = Modifier.weight(1f).height(46.dp),
                    onClick = {onSubmitClick()}
                )
            }
            Spacer(modifier = Modifier.height(25.dp))






        }
    }
}