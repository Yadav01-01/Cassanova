package com.bussiness.cassanova.model

data class Reservation(
    val tableId: String,
    val date: String,
    val time: String,
    val itemCount: Int,
    val totalAmount: Double,
    val guests: Int,
    val specialRequest: String?,
    val occasion: String?,
    val occasionIcon: Int? = null,
    val pointsEarned: String,
    val message: String? = null,
    val status: String? = null // e.g., "Cancelled", null if active
)

