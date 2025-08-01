package com.bussiness.cassanova.ui.screen.auth

import android.app.Activity
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.base.VerificationType
import com.bussiness.cassanova.ui.component.CommonButton
import kotlinx.coroutines.delay
import kotlin.compareTo
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.ui.platform.LocalContext
import com.bussiness.cassanova.navigation.Routes
import com.bussiness.cassanova.ui.component.StatusBarPending
import com.bussiness.cassanova.ui.component.dialog.CongratulationsDialog

@Composable
fun CassanovaVerifyOTPScreen(navController: NavHostController, type: VerificationType) {
//    var type: VerificationType= VerificationType.PHONE
    var otp by remember { mutableStateOf("") }

    var timeLeft by remember { mutableStateOf(59) }
    var errorMessage by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // Timer effect
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    LaunchedEffect(otp) {
        if (errorMessage.isNotEmpty() && otp.isNotEmpty()) {
            errorMessage = ""
        }
    }

    // Validation function
    fun validateOTP(): Boolean {
        return when {
            otp.isEmpty() -> {
                errorMessage = "Please enter OTP"
                false
            }

            otp.length < 5 -> {
                errorMessage = "Please enter complete 5-digit OTP"
                false
            }

            otp != "12345" -> {
                errorMessage = "Invalid OTP. Please try again"
                false
            }

            else -> {
                errorMessage = ""
                true
            }
        }
    }
    Column {
        StatusBarPending()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize().padding(horizontal = 20.dp),
        ) {

            Spacer(Modifier.height(110.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cassanova_logo),
                    modifier = Modifier
                        .width(200.dp)
                        .height(143.dp),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Verify Your Number",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text =
                    "We've sent a 5-digit code to",

                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFFAAAAAA)
                ),
                fontFamily = FontFamily(Font(R.font.urbanist_semibold))

            )
            Text(
                text = if (type == VerificationType.PHONE)
                    "+1 (555) 555-5555"
                else

                    "user@email.com",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White
                ),
                fontFamily = FontFamily(Font(R.font.urbanist_semibold))
            )

            Spacer(modifier = Modifier.height(32.dp))

            // OTP Input Fields
            CassanovaOtpInputField(
                otp = otp,
                onOtpTextChange = { otp = it }
            )

            // Error message display
            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Red,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium))
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            CommonButton(title = "Verify & Continue", onClick = {
                if (validateOTP()) {
                    showDialog = true
                }
            })

            Spacer(modifier = Modifier.height(24.dp))

            // Resend section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Didn't receive the verification code? ",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    color = Color(0xFFAAAAAA)
                )
                if (timeLeft > 0) {
                    // When timer is running - grey and not clickable
                    Text(
                        text = "RESEND",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        color = Color(0xFFAAAAAA) // Grey color
                    )
                } else {
                    // When timer is finished - white and clickable
                    Text(
                        text = "RESEND",
                        modifier = Modifier.clickable {
                            timeLeft = 59 // Reset timer when clicked
                        },
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                        color = Color(0xFFFFFFFF) // White color
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Resend verification code in ",
                    fontSize = 15.sp,
                    color = Color(0xFFAAAAAA),
                    fontFamily = FontFamily(Font(R.font.urbanist_medium))
                )

                Text(
                    text = "${String.format("%02d:%02d", timeLeft / 60, timeLeft % 60)} secs",
                    fontSize = 15.sp,
                    color = Color(0xFFFFFFFF),
                    fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                )
            }
        }

        // Back arrow at top
        Icon(
            painter = painterResource(id = R.drawable.ic_back_icon),
            contentDescription = "Back",
            tint = Color(0xFFC09D46),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp)
                .size(24.dp)
                .clickable { navController.popBackStack() }

        )
        if (showDialog) {
            CongratulationsDialog(onClick = {
                navController.navigate(Routes.NOTIFICATION_PERMISSION_SCREEN)
            })
        }
    }
}

}

@Composable
fun CassanovaOtpInputField(
    otp: String,
    onOtpTextChange: (String) -> Unit,
    hasError: Boolean = false
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Hidden TextField for input handling
        BasicTextField(
            value = otp,
            onValueChange = { newValue ->
                if (newValue.length <= 5 && newValue.all { it.isDigit() }) {
                    onOtpTextChange(newValue)
                    if (newValue.length == 5) {
                        focusManager.clearFocus()
                    }
                }
            },
            modifier = Modifier
                .size(1.dp, 1.dp)
                .alpha(0f)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )

        // Visible OTP boxes
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (hasError) Color.Red else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            focusRequester.requestFocus()
                            // This will show the keyboard again
                            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            val view = (context as? Activity)?.currentFocus ?: return@clickable
                            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (index < otp.length) otp[index].toString() else "",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            color = Color.White
                        )
                    )
                }
            }
        }
    }

    // Request focus when composable appears
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview(showBackground = true)
@Composable
fun CassanovaVerifyOTPScreenPreview() {
    val dummyNavController = rememberNavController()
    CassanovaVerifyOTPScreen(navController = dummyNavController, VerificationType.PHONE)
}