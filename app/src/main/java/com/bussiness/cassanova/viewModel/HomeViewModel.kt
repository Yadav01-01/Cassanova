package com.bussiness.cassanova.viewModel

import androidx.lifecycle.ViewModel
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.EventCarouselData
import com.bussiness.cassanova.model.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    // User data
    private val _userName = MutableStateFlow("Sarah Jonson")
    val userName: StateFlow<String> = _userName

    private val _greeting = MutableStateFlow("Good Evening")
    val greeting: StateFlow<String> = _greeting

    private val _userStatus = MutableStateFlow("Your Cassanova journey continues...")
    val userStatus: StateFlow<String> = _userStatus

    private val _loyaltyPoints = MutableStateFlow(120)
    val loyaltyPoints: StateFlow<Int> = _loyaltyPoints

    // Menu items
    private val _menuItems = MutableStateFlow(listOf(
        MenuItem("Truffle Parmesan...", "Appetizers", "$25", R.drawable.dummy_social_media_post),
        MenuItem("Charcoal-Grilled T...", "Main Course", "$35", R.drawable.dummy_baby_pic),
        MenuItem("Golden Honey...", "Signature Dish", "$55", R.drawable.dummy_social_media_post)
    ))
    val menuItems: StateFlow<List<MenuItem>> = _menuItems

    // Events
    private val _events = MutableStateFlow(listOf(
        EventCarouselData(
            "VIP Lounge Access",
            "Saturday, 27 May - 10:00 AM",
            "From wine tastings to rooftop sessions — only for our elite guests",
            R.drawable.dummy_social_media_post
        ),
        EventCarouselData(
            "Exclusive Dining",
            "Sunday, 28 May - 7:00 PM",
            "Curated culinary experiences with world-renowned chefs",
            R.drawable.dummy_social_media_post
        ),
        EventCarouselData(
            "Private Concert",
            "Monday, 29 May - 8:30 PM",
            "Intimate musical performances in our exclusive venue",
            R.drawable.dummy_social_media_post
        )
    ))
    val events: StateFlow<List<EventCarouselData>> = _events

    // Update functions
    fun updateUserName(newName: String) {
        _userName.value = newName
    }

    fun updateGreeting(newGreeting: String) {
        _greeting.value = newGreeting
    }

    fun updateUserStatus(newStatus: String) {
        _userStatus.value = newStatus
    }

    fun updateLoyaltyPoints(newPoints: Int) {
        _loyaltyPoints.value = newPoints
    }

    fun addMenuItem(newItem: MenuItem) {
        _menuItems.value = _menuItems.value + newItem
    }

    fun addEvent(newEvent: EventCarouselData) {
        _events.value = _events.value + newEvent
    }
}