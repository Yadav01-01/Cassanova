package com.bussiness.cassanova.model

data class Order(
    val orderNumber: String,
    val date: String,
    val time: String,
    val redeemedPoints: String,
    val amount: String,
    val itemsCount: Int,
    val earnedPoints: String
)
