package com.bussiness.cassanova.model

data class NotificationData(
    val id: Int,
    val profileImage: Int, // URL or resource identifier
    val message: String,
    val timestamp: String
)
