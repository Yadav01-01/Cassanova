package com.bussiness.cassanova.ui.screen.main.bookings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.bussiness.cassanova.model.BookingItem
import com.bussiness.cassanova.ui.component.BookingCard

@Composable
fun UpcomingBookingsScreen() {
    val bookingList = listOf(
        BookingItem(
            tableId = "#A21",
            title = "Table ID - #A21",
            time = "Monday, 2 June – 10:00 AM",
            guestCount = "5",
            specialRequest = "Birthday Party",
            message = "We’ve locked in your spot. See You Soon!",
            highlightText = "See You Soon!",
            status = "Reserved"
        ),
        BookingItem(
            tableId = null,
            title = "Request Received",
            time = "Monday, 2 June – 10:00 AM",
            guestCount = "5",
            specialRequest = "Other",
            message = "You’ll receive a Confirmation shortly.",
            highlightText = "Confirmation",
            status = "In Process"
        )
    )

    LazyColumn {
        items(bookingList) { booking ->
            BookingCard(
                booking = booking,
                onStatusClick = { /* Handle */ },
                onMenuClick = { /* Handle */ }
            )
        }
    }

}