package com.example.contactapp1.presentation.navigation

sealed class Routes (var route : String) {
    object AddEdit : Routes("add_edit_screen")
    object Home : Routes("home_screen")
}