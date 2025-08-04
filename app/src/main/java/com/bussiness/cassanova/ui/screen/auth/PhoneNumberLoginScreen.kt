package com.bussiness.cassanova.ui.screen.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.base.ErrorMessages
import com.bussiness.cassanova.base.VerificationType
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.StatusBarPending
import com.bussiness.komposecountrycodepicker.component.KomposeCountryCodePicker
import com.bussiness.komposecountrycodepicker.component.rememberKomposeCountryCodePickerState

@Composable
fun PhoneNumberLoginScreen(navController: NavController = rememberNavController()) {
    var isPhoneValid by remember { mutableStateOf(true) }
    var showError by remember { mutableStateOf(false) }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    val state = rememberKomposeCountryCodePickerState(
//            limitedCountries = listOf("KE", "UG", "TZ", "RW", "SS", "Togo", "+260", "250", "+211", "Mali", "Malawi"),
//            priorityCountries = listOf("SA", "KW", "BH", "QA"),
//            showCountryCode = true,
//            showCountryFlag = true,
//            defaultCountryCode = "KE",
    )

    fun validatePhoneNumber(phone: String): Boolean {
        return phone.length == 10 && phone.all { it.isDigit() }
    }

    fun onSendCodeClick() {
        val isValid = validatePhoneNumber(phoneNumber)
        isPhoneValid = isValid
        showError = !isValid

        if (isValid) {
            // Handle send code logic here
            println("Sending code to: +1$phoneNumber")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column (Modifier
            .fillMaxSize()){
           // StatusBarPending()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 20.dp)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(90.dp))

                // Logo Section
                Image(
                    painter = painterResource(id = R.drawable.ic_cassanova_logo),
                    modifier = Modifier
                        .width(200.dp)
                        .height(143.dp),
                    contentDescription = ""
                )

            }

            Spacer(modifier = Modifier.height(50.dp))

            // Title
            Text(
                text = stringResource(R.string.lets_get_you_in),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = stringResource(R.string.enter_mobile_number),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                    color = Color(0xFFAAAAAA),
                    //textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Phone Input Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Country Code Picker
//                Box(
//                    modifier = Modifier
//                        .border(
//                            1.dp,
//                            if (showError && !isPhoneValid) Color.Red else Color.Gray,
//                            RoundedCornerShape(8.dp)
//                        )
//                        .background(
//                            Color.Transparent,
//                            RoundedCornerShape(8.dp)
//                        )
//                ) {


                KomposeCountryCodePicker(
                    modifier = Modifier.border(
                        1.dp,
                        if (showError && !isPhoneValid) Color.Red else Color.Gray,
                        RoundedCornerShape(8.dp)
                    )
                        .width(85.dp).wrapContentHeight(),
                    state = state,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color(0xFF626D77),
                        unfocusedTextColor = Color(0xFF626D77),
                        cursorColor = Color(0xFFD4AF37)
                    ),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    ),
                    interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    Log.e("CountryCodePicker", "clicked")
                                }
                            }
                        }
                    },
                    text = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    error = showError && !isPhoneValid,
                    showOnlyCountryCodePicker = false,
                    showDropdownIcon = false,
                    placeholder = {
                        Text(
                            text = "Code",
                            color = Color(0xFF949494),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                        )
                    },
                    trailingIcon = null,
//                            {
////                            Box(
////                                modifier = Modifier
////                                    .width(24.dp)
////                                    .height(24.dp)
////                                    .offset(x = (-24).dp) // Pull back to overlap the dropdown icon
////                                    .background(Color.Transparent)
////                            )
//                        },

                    // countrySelectionDialogContainerColor = Color.Black,
                    // countrySelectionDialogContentColor = Color.White,
                    enabled = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),


                    )


                //  }


                Spacer(modifier = Modifier.width(12.dp))

                // Phone Number Input
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                            phoneNumber = it
                            if (showError) {
                                isPhoneValid = validatePhoneNumber(it)
                                showError = !isPhoneValid
                            }
                        }
                    },
                    trailingIcon = {
                        if (phoneNumber.length == 10 && phoneNumber.all { char -> char.isDigit() }) {
                            Image(painter = painterResource(R.drawable.ic_check_icon),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp))
                        }

                    },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.hint_phone_number),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (showError && !isPhoneValid) Color.Red else Color(
                            0xFFD4AF37
                        ),
                        unfocusedBorderColor = if (showError && !isPhoneValid) Color.Red else Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color(0xFFD4AF37)
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    isError = showError && !isPhoneValid,
                    shape = RoundedCornerShape(10.dp)
                )
            }

            // Error Message
            if (showError && !isPhoneValid) {
                Text(
                    text = ErrorMessages.enterValidNumber,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            CommonButton(title = stringResource(R.string.send_code), onClick = {
                //   onSendCodeClick()
                navController.navigate("${Routes.VERIFY_OTP_SCREEN}/${VerificationType.PHONE.name}")
            })

            Spacer(modifier = Modifier.height(50.dp))

            // Continue with Email
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier.clickable {

                        println("Continue with email clicked")
                        navController.navigate(Routes.EMAIL_LOGIN_SCREEN)

                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_email_icon),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.continue_with_email),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    )
                }
            }
        }
    }
    }
}


@Preview(showBackground = true)
@Composable
fun PhoneNumberLoginScreenPreview() {
    PhoneNumberLoginScreen()
}