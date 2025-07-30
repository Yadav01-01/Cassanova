package com.bussiness.cassanova.viewModel

import androidx.lifecycle.ViewModel
import com.bussiness.cassanova.R
import com.bussiness.cassanova.model.MenuItem
import com.bussiness.cassanova.model.MenuListData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {
    private val _menuItems = MutableStateFlow<List<MenuListData>>(emptyList())
    val menuItems: StateFlow<List<MenuListData>> = _menuItems.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _filteredMenuItems = MutableStateFlow<List<MenuListData>>(emptyList())
    val filteredMenuItems: StateFlow<List<MenuListData>> = _filteredMenuItems.asStateFlow()

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        val sampleMenuItems = listOf(
            MenuListData(
                id = 1,
                name = "Truffle Parmesan Fries",
                category = "Appetizers",
                price = "$25",
                description = "Hand-cut fries tossed in white truffle oil, parmesan, and herb oil.",
                imageRes = R.drawable.dummy_social_media_post
            ),
            MenuListData(
                id = 2,
                name = "Golden Hour Martini",
                category = "Signature Cocktails",
                price = "$35",
                description = "Vodka, passionfruit, and edible gold flakes - elegant and bright",
                imageRes = R.drawable.dummy_social_media_post,
                isLocked = true
            ),
            MenuListData(
                id = 3,
                name = "Molten Gold Lava Cake",
                category = "Desserts",
                price = "$28",
                description = "Dark chocolate cake with hazelnut center and gold leaf dust",
                imageRes = R.drawable.dummy_social_media_post
            ),
            MenuListData(
                id = 4,
                name = "Charcoal-Grilled Tenderloin",
                category = "Mains",
                price = "$65",
                description = "Premium beef tenderloin with seasonal vegetables",
                imageRes = R.drawable.dummy_social_media_post
            ),
            MenuListData(
                id = 5,
                name = "Seared Scallops",
                category = "Appetizers",
                price = "$32",
                description = "Pan-seared scallops with cauliflower puree and pancetta",
                imageRes = R.drawable.dummy_social_media_post
            ),
            MenuListData(
                id = 6,
                name = "Wagyu Beef Burger",
                category = "Mains",
                price = "$45",
                description = "Premium wagyu beef with truffle aioli and aged cheddar",
                imageRes = R.drawable.dummy_social_media_post,
                isLocked = true
            )
        )
        _menuItems.value = sampleMenuItems
        _filteredMenuItems.value = sampleMenuItems
    }

    fun updateSearchText(newText: String) {
        _searchText.value = newText
        filterMenuItems(newText)
    }

    private fun filterMenuItems(query: String) {
        val filtered = if (query.isEmpty()) {
            _menuItems.value
        } else {
            _menuItems.value.filter { item ->
                item.name.contains(query, ignoreCase = true) ||
                        item.category.contains(query, ignoreCase = true) ||
                        item.description.contains(query, ignoreCase = true)
            }
        }
        _filteredMenuItems.value = filtered
    }
}