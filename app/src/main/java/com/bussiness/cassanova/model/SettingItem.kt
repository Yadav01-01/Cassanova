package com.bussiness.cassanova.model


data class SettingItem(val icon: Int,
                       val title: String,
                       val hasToggle: Boolean = false,
                       val isToggleEnabled: Boolean = false,
    )
