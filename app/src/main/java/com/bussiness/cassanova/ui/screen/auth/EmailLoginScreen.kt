package com.bussiness.cassanova.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bussiness.cassanova.ui.component.input.CommonButton

@Composable
fun EmailLoginScreen(navController: NavController = rememberNavController()) {
    var isEmailValid by remember { mutableStateOf(true) }
    var showError by remember { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()

    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return emailRegex.matches(email)
    }

    fun onSendCodeClick() {
        val isValid = validateEmail(email)
        isEmailValid = isValid
        showError = !isValid

        if (isValid) {
            // Handle send code logic here
            println("Sending code to email: $email")
            navController.navigate("${Routes.VERIFY_OTP_SCREEN}/${VerificationType.EMAIL.name}")
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            //StatusBarPending()
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
                    text = stringResource(R.string.enter_email),
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


                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            if (showError) {
                                isEmailValid = validateEmail(it)
                                showError = !isEmailValid
                            }
                        },
                        modifier = Modifier.weight(1f),
                        leadingIcon = {
//
                            Image(
                                painter = painterResource(id = R.drawable.ic_email_icon_white),
                                contentDescription = "Email icon",

                                modifier = Modifier
                                    .height(24.dp)
                                    .width(24.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.email_id),
                                color = Color(0xFF808080),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (showError && !isEmailValid) Color.Red else Color(
                                0xFFD4AF37
                            ),
                            unfocusedBorderColor = if (showError && !isEmailValid) Color.Red else Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color(0xFF808080),
                            cursorColor = Color(0xFFD4AF37)
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,  // This sets the email keyboard type
                            autoCorrect = false,
                        ),
                        singleLine = true,
                        isError = showError && !isEmailValid,
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                // Error Message
                if (showError && !isEmailValid) {
                    Text(
                        text = ErrorMessages.enterValidEmail,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))


                CommonButton(title = stringResource(R.string.send_code), onClick = {
                    onSendCodeClick()

                })

                Spacer(modifier = Modifier.height(50.dp))

                // Continue with Email
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Row(
                        modifier = Modifier.clickable {
                            // Handle email login
                            println("Continue with email clicked")
                            navController.navigate(Routes.PHONE_NUMBER_LOGIN_SCREEN)
                           // navController.navigate(Routes.EXAMPLE_SCREEN)
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_round_phone),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.continue_with_phone),
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
fun EmailLoginScreenPreview() {
    EmailLoginScreen()
}