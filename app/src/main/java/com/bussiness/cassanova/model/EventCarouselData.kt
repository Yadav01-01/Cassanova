package com.bussiness.cassanova.model

data class EventCarouselData(val title: String,
                             val date: String,
                             val description: String,
                             val imageRes: Int, // Replace with actual image resources
                             val buttonText: String = "View Event Details")
