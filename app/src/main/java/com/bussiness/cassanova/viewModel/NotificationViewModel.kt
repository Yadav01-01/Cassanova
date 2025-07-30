package com.bussiness.cassanova.viewModel

import androidx.lifecycle.ViewModel
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.NotificationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor() : ViewModel() {
    private val _notifications = MutableStateFlow<List<NotificationData>>(emptyList())
    val notifications: StateFlow<List<NotificationData>> = _notifications.asStateFlow()

    init {
        loadNotifications()
    }

    private fun loadNotifications() {

        val sampleNotifications = listOf(
            NotificationData(
                id = 1,
                profileImage = R.drawable.dummy_social_media_post,
                message = "Lorem Ipsum is simply dummy text",
                timestamp = "8/27/2022"
            ),
            NotificationData(
                id = 2,
                profileImage = R.drawable.dummy_social_media_post,
                message = "Lorem Ipsum is simply dummy text",
                timestamp = "8/27/2022"
            ),
            NotificationData(
                id = 3,
                profileImage = R.drawable.dummy_social_media_post,
                message = "Lorem Ipsum is simply dummy text",
                timestamp = "8/27/2022"
            ),
            NotificationData(
                id = 4,
                profileImage = R.drawable.dummy_social_media_post,
                message = "Lorem Ipsum is simply dummy text",
                timestamp = "8/27/2022"
            ),
            NotificationData(
                id = 5,
                profileImage = R.drawable.dummy_social_media_post,
                message = "Lorem Ipsum is simply dummy text",
                timestamp = "8/27/2022"
            ),
            NotificationData(
                id = 6,
                profileImage = R.drawable.dummy_social_media_post,
                message = "Lorem Ipsum is simply dummy text",
                timestamp = "8/27/2022"
            )
        )
        _notifications.value = sampleNotifications
    }
}