package com.bussiness.cassanova.ui.screen.auth

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bussiness.cassanova.ui.component.spinner.CustomPowerSpinner

@Composable
fun ExampleScreen(navController: NavHostController) {
    var selectedReason by remember { mutableStateOf("Select Reason*") }
    val reasons = listOf(
        "Booking Made by Mistake",
        "Plans Postponed",
        "Unable to Reach Venue",
        "Unexpected Emergency",
        "Other"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(24.dp)
    ) {
        CustomPowerSpinner(
            selectedText = selectedReason,
            onSelectionChanged = { reason ->
                selectedReason = reason
            },
            menuPadding = 24.dp,
            reasons = reasons
        )

        // Demo display
        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Selected:",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = selectedReason,
                    color = Color(0xFFB0B0B0),
                    fontSize = 14.sp
                )
            }
        }
    }
}

// Required imports
