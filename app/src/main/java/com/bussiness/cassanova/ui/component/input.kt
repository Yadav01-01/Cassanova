package com.bussiness.cassanova.ui.component

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.bussiness.cassanova.R
import com.bussiness.cassanova.ui.intro.OnboardingScreen
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width

import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

val gridScrollConnection = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        // Allow parent to consume scroll if needed
        return Offset.Zero
    }

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
        // Prevent parent from scrolling when grid is scrolling
        return available
    }
}

@Composable
fun CommonButton(
    onClick: () -> Unit = {},
    title: String, modifier: Modifier = Modifier, fontSize: TextUnit = 18.sp, radius: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable {
                onClick()
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFC7A65E),
                        Color(0xFFFBE29A),
                        Color(0xFFBE9B43)
                    )
                ),
                shape = RoundedCornerShape(radius)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
        )
    }
}

@Composable
fun CommonArrowButton(
    onClick: () -> Unit = {},
    title: String, modifier: Modifier = Modifier, fontSize: TextUnit = 18.sp, radius: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable {
                onClick()
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFC7A65E),
                        Color(0xFFFBE29A),
                        Color(0xFFBE9B43)
                    )
                ),
                shape = RoundedCornerShape(radius)
            ),
        contentAlignment = Alignment.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = fontSize,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
            )

            Icon(
                painter = painterResource(
                    id = R.drawable.ic_right_arrow
                ),
                contentDescription = "right icon", modifier = Modifier.wrapContentHeight()
            )
        }


    }
}

@Composable
fun SetStatusBarColor(darkTheme: Boolean = isSystemInDarkTheme()) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        window.statusBarColor = Color.Transparent.value.toInt()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }
}

@Composable
fun StatusBarPending() {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    Spacer(Modifier.height(statusBarPadding.calculateTopPadding()))
    SetStatusBarColor()
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun CommonButtonPreview() {
//    CommonButton(
//        title = "Send Code",
//        )
//}


@Composable
fun BackButtonWithArrow(
    onBackPressed: () -> Unit = {},
    title: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.Black)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Arrow Icon
            Icon(
                painter = painterResource(id = R.drawable.ic_back_icon),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackPressed() }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_semibold))
            )
        }
    }
}

@Preview
@Composable
fun BackButtonWithArrowPreview() {
    Column {
        BackButtonWithArrow(
            onBackPressed = { /* Handle back navigation */ },
            "Choose a different date"
        )
    }
}





@Composable
fun DashedDivider(
    color: Color = Color.Gray,
    thickness: Dp = 1.dp,
    dashLength: Dp = 10.dp,
    gapLength: Dp = 5.dp,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
    ) {
        drawLine(
            color = color,

            strokeWidth = thickness.toPx(),
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(
                    dashLength.toPx(),
                    gapLength.toPx()
                ),
                phase = 0f
            )
        )
    }
}


@Composable
fun CommonUnlockButton(
    onClick: () -> Unit = {},
    title: String, modifier: Modifier = Modifier, fontSize: TextUnit = 16.sp, radius: Dp = 12.dp
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .height(45.dp)
            .padding(horizontal = 10.dp)
            .clickable {
                onClick()
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFC7A65E),
                        Color(0xFFFBE29A),
                        Color(0xFFBE9B43)
                    )
                ),
                shape = RoundedCornerShape(radius)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(12.dp))
        Image(painter = painterResource(id = R.drawable.ic_unlock_icon),
            contentDescription = "",
            modifier= Modifier.wrapContentSize())

        Spacer(Modifier.width(10.dp))

        Text(
            text = title,
            color = Color.Black,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
        )
        Spacer(Modifier.width(12.dp))
    }
}


@Composable
fun CommonWhiteBorderButton(
    onClick: () -> Unit = {},
    title: String, modifier: Modifier = Modifier, fontSize: TextUnit = 18.sp, radius: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable {
                onClick()
            }
            .border(width = 1.dp,color= Color.White, shape = RoundedCornerShape(10.dp))
            ,

        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
        )
    }
}

