package com.bussiness.cassanova.model

data class BookingItem(
    val tableId: String?,
    val title: String,
    val time: String,
    val guestCount: String,
    val specialRequest: String,
    val message: String,
    val highlightText: String,
    val status: String
)

