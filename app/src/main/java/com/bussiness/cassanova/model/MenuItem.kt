package com.bussiness.cassanova.model

data class MenuItem(
    val name: String,
    val category: String,
    val price: String,
    val imageRes: Int = 0 // In real app, use actual image resources
)
