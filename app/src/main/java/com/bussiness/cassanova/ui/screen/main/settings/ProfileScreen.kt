package com.bussiness.cassanova.ui.screen.main.settings

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.component.CommonButton
import com.bussiness.cassanova.ui.component.SettingHeader
import java.io.File

@Composable
fun ProfileScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("Sarah Jonson") }
    var email by remember { mutableStateOf("sarah.jonson@example.com") }
    var phone by remember { mutableStateOf("+1 (XXX) XXX-XXXX") }
    var location by remember { mutableStateOf("01-14-1997") }
    var clickedEdit by remember { mutableStateOf(false) }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var showImagePickerDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
    }

    val launcherCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            val uri = saveBitmapToCache(context, it)
            profileImageUri = uri
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launcherCamera.launch(null)
        } else {
            Toast.makeText(context, "Camera permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    fun launchCameraWithPermissionCheck() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) -> {
                launcherCamera.launch(null)
            }
            else -> {
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SettingHeader(title = "Edit Profile", onBackClick = {
            navController.popBackStack()
        })

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Profile Image Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                ProfileImageWithCamera(
                    profileImage = profileImageUri?.let { rememberAsyncImagePainter(it) }
                        ?: painterResource(id = R.drawable.dummy_profile_picture),
                    cameraIcon = painterResource(id = R.drawable.ic_upload_icon),
                    onCameraClick = {
                        showImagePickerDialog = true
                    },
                    clickedEdit = clickedEdit
                )
            }

            if (showImagePickerDialog) {
                AlertDialog(
                    onDismissRequest = { showImagePickerDialog = false },
                    title = { Text("Select Option") },
                    buttons = {
                        Column(Modifier.padding(16.dp)) {
                            Text("Camera", Modifier.clickable {
                                showImagePickerDialog = false
                                launchCameraWithPermissionCheck()
                            }.padding(8.dp))

                            Text("Gallery", Modifier.clickable {
                                showImagePickerDialog = false
                                launcherGallery.launch("image/*")
                            }.padding(8.dp))
                        }
                    }
                )
            }

            // Profile Form Fields
            ProfileInfoItem(
                label = "Name",
                value = name,
                onValueChange = { name = it },
                icon = painterResource(id = R.drawable.name_ic),
                isEditable = clickedEdit
            )

            Spacer(Modifier.height(16.dp))

            ProfileInfoItem(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                icon = painterResource(id = R.drawable.email_ic),
                isEditable = clickedEdit
            )

            Spacer(Modifier.height(16.dp))

            ProfileInfoItem(
                label = "Phone",
                value = phone,
                onValueChange = { phone = it },
                icon = painterResource(id = R.drawable.call_ic),
                isEditable = clickedEdit
            )

            Spacer(Modifier.height(16.dp))

            ProfileInfoItem(
                label = "Location",
                value = location,
                onValueChange = { location = it },
                icon = painterResource(id = R.drawable.ic_cake_icon),
                isEditable = clickedEdit
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                if (!clickedEdit) {
                    CommonButton(
                        title = "Update Now",
                        onClick = { clickedEdit = true },
                        modifier = Modifier.height(46.dp)
                    )
                } else {
                    CommonButton(
                        title = "Save Changes",
                        onClick = { clickedEdit = false },
                        modifier = Modifier.height(46.dp)
                    )
                }
            }
        }


    }
}


@Composable
fun ProfileImageWithCamera(
    profileImage: Painter,
    cameraIcon: Painter,
    onCameraClick: () -> Unit,
    clickedEdit: Boolean
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .size(145.dp)
            .padding(8.dp),

        ) {
        // Circular Profile Image
        Image(
            painter = profileImage,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)).align(Alignment.Center)

        )

        // Camera Icon Overlay
        if (clickedEdit){


        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .clickable { onCameraClick() },
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = cameraIcon,
                contentDescription = "Camera Icon",
                modifier = Modifier.wrapContentSize()
            )
        }
        }
    }
}

@Composable
fun ProfileInfoItem(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: Painter,
    modifier: Modifier = Modifier,
    isEditable: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.dp,
                color = if (isEditable) Color(0xFFD4AF37) else Color(0xFF333333),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            // Icon
            Image(
                painter = icon,
                contentDescription = "$label icon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Text Field
            BasicTextField(
                value = value,
                onValueChange = { if (isEditable) onValueChange(it) },
                enabled = isEditable,
                textStyle = TextStyle(
                    color = if (isEditable) Color.White else Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_medium))
                ),
                cursorBrush = SolidColor(Color(0xFFD4AF37)),
                modifier = Modifier.weight(1f)
            )
        }
    }
}



fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
    val file = File(context.cacheDir, "profile_pic_${System.currentTimeMillis()}.png")
    file.outputStream().use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        it.flush()
    }
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        ProfileScreen(navController = navController)
    }
}
