package com.bussiness.cassanova.ui.screen.auth

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bussiness.cassanova.R

@Composable
fun AnimatedNotificationIcon(modifier: Modifier = Modifier) {
    // Animation values
    val infiniteTransition = rememberInfiniteTransition(label = "notification_animation")

    // Scale animation for the bell icon
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale_animation"
    )

    // Ripple animation
    val rippleScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseOut),
            repeatMode = RepeatMode.Restart
        ),
        label = "ripple_animation"
    )

    val rippleAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseOut),
            repeatMode = RepeatMode.Restart
        ),
        label = "ripple_alpha"
    )

    Box(
        modifier = modifier.size(198.dp),
        contentAlignment = Alignment.Center
    ) {
        // Animated ripple circles
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawAnimatedRipples(
                center = center,
                scale = rippleScale,
                alpha = rippleAlpha,
                middleColor = Color(0xFF919191),
                outerColor = Color(0xFF757575)
            )
        }

        // Bell icon with scale animation
            Image(
                painter = painterResource(id = R.drawable.notification_icon2),
                contentDescription = "Notification Icon",
                modifier = Modifier.wrapContentSize()
            )
    }
}

private fun DrawScope.drawAnimatedRipples(
    center: androidx.compose.ui.geometry.Offset,
    scale: Float,
    alpha: Float,
    middleColor: Color,
    outerColor: Color
) {
    // Outer circle
    drawCircle(
        color = outerColor.copy(alpha = alpha * 0.3f),
        radius = 90.dp.toPx() * scale,
        center = center
    )

    // Middle circle
    drawCircle(
        color = middleColor.copy(alpha = alpha * 0.5f),
        radius = 65.dp.toPx() * scale,
        center = center
    )

    // Inner circle
    drawCircle(
        color = Color.Black.copy(alpha = alpha * 0.8f),
        radius = 40.dp.toPx() * scale,
        center = center
    )
}