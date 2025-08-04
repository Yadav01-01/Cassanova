package com.bussiness.cassanova.ui.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bussiness.cassanova.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToNext: () -> Unit) {
    LaunchedEffect(true) {
        delay(3000) // simulate loading time
        onNavigateToNext()
    }


    Column(Modifier
        .fillMaxSize()) {

       // StatusBarPending()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(R.drawable.ic_cassanova_logo), contentDescription = null, modifier = Modifier.width(250.dp))
    }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onNavigateToNext = { /* No-op for preview */ })
}