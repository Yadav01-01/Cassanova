package com.bussiness.cassanova.ui.screen.main.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.CommonOutlinedTextField
import com.bussiness.cassanova.ui.component.InputField
import com.bussiness.cassanova.ui.component.SettingHeader
import com.bussiness.cassanova.ui.theme.gradientBrush
import com.bussiness.cassanova.viewModel.ContactUsViewModel

@Composable
fun ContactUsScreen(navController: NavHostController, viewModel: ContactUsViewModel = hiltViewModel()) {
    val name by viewModel.name.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val email by viewModel.email.collectAsState()
    val subject by viewModel.subject.collectAsState()
    val query by viewModel.query.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SettingHeader(title = "Contact Us", onBackClick = {
            navController.popBackStack()
        })

        Column(Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 20.dp).verticalScroll(rememberScrollState())) {
            Text(
                text = "Update Reservation Date",
                style = TextStyle(
                    brush = gradientBrush
                ),
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
            )

            Image(
                painter = painterResource(R.drawable.ic_help_support_feedback_icon),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().height(260.dp)
            )

            Spacer(Modifier.height(30.dp))

            CommonOutlinedTextField(
                value = name,
                onValueChange = { viewModel.updateName(it) },
                hintText = "Enter your name",
                leadingIconResId = R.drawable.name_ic,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                keyboardType= KeyboardType.Text
            )

            Spacer(Modifier.height(20.dp))

            CommonOutlinedTextField(
                value = phone,
                onValueChange = { viewModel.updatePhone(it) },
                hintText = "+1 (XXX) XXX-XXXX",
                leadingIconResId = R.drawable.call_ic,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                keyboardType= KeyboardType.Phone
            )
            Spacer(Modifier.height(20.dp))
            CommonOutlinedTextField(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                hintText = "Email ID",
                leadingIconResId = R.drawable.email_ic,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                keyboardType= KeyboardType.Email
            )
            Spacer(Modifier.height(20.dp))
            CommonOutlinedTextField(
                value = subject,
                onValueChange = { viewModel.updateSubject(it) },
                hintText = "Subject",
                leadingIconResId = R.drawable.subject_ic,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                keyboardType= KeyboardType.Text
            )
            Spacer(Modifier.height(20.dp))
            InputField(
                modifier = Modifier.height(106.dp),
                placeholder = "Enter Your Query",
                input = query,
                onValueChange = { viewModel.updateQuery(it)}
            )
            Spacer(Modifier.height(20.dp))
            CommonButton(
                title = "Submit",
                fontSize = 18.sp,
                modifier = Modifier.height(46.dp),
                onClick = {
                    viewModel.submitContactForm()
                }
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactUsScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        ContactUsScreen(navController = navController)
    }
}