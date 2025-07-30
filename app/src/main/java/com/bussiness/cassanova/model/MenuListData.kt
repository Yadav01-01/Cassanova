package com.bussiness.cassanova.model

data class MenuListData( val id: Int,
                         val name: String,
                         val category: String,
                         val price: String,
                         val description: String,
                         val imageRes: Int, // In real app, use URL or drawable resource
                         val isLocked: Boolean = false)
