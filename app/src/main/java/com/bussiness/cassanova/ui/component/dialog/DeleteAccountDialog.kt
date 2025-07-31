package com.bussiness.cassanova.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.CommonWhiteBorderButton

@Composable
fun DeleteAccountDialog(onDeleteClick: () -> Unit= {},
                        onCancelClick: () -> Unit= {},
                        onDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x804C4C4C)).padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Main card content
                Image(
                    painter = painterResource(id = R.drawable.ic_cross_icon),
                    contentDescription = "Close",

                    modifier = Modifier
                        .clickable {
                            onDismiss()
                        }
                        .align(Alignment.TopCenter)
                        .size(40.dp)
                        .clip(CircleShape)
                        .padding(bottom = 8.dp)
                        .align(Alignment.TopStart)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 40.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    // Main content
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Icon (you can replace with your actual icon resource)


                        Image(
                            painter = painterResource(id = R.drawable.ic_delete_dialog_icon),
                            contentDescription = "Close",
                            modifier = Modifier
                                .wrapContentSize()

                        )

                        // Title
                        Text(
                            text = "Delete Account !",
                            fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )

                        // Description
                        Text(
                            text = "Are you sure you want to delete your\n account with Cassanova Bar & Lounge?\n  All your data, reservation history, and\n loyalty points will be permanently deleted.\n This action cannot be undone.",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            color = Color.White,
                            lineHeight = 24.sp
                        )

                        Spacer(Modifier.height(5.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CommonButton(
                                title = "Delete",
                                modifier = Modifier.weight(1f).height(46.dp),
                                onClick = { onDeleteClick() },
                                fontSize = 18.sp,
                                radius = 10.dp
                            )

                            CommonWhiteBorderButton(
                                title = "Cancel",
                                modifier = Modifier.weight(1f).height(46.dp),
                                onClick = { onCancelClick() },
                                fontSize = 18.sp,
                                radius = 10.dp

                            )


                        }

                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountDialogPreview() {
    DeleteAccountDialog()
}

