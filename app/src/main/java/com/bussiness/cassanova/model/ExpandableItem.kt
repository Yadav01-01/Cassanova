package com.bussiness.cassanova.model

data class ExpandableItem(
    val id: Int,
    val title: String,
    val content: String,
    val isExpanded: Boolean = false
)