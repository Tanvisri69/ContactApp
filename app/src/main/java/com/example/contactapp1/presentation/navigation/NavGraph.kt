package com.example.contactapp1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contactapp1.presentation.ContactViewModel
import com.example.contactapp1.presentation.Screen.AddEditScreen
import androidx.compose.runtime.getValue
import com.example.contactapp1.presentation.Screen.HomeScreen

@Composable
fun NavGraph(navHostController: NavHostController, viewModel: ContactViewModel){
    val state by viewModel.state.collectAsState()
    NavHost(navController = navHostController, startDestination = Routes.Home.route){
        composable(Routes.AddEdit.route){
            AddEditScreen(navHostController = navHostController, state = viewModel.state.collectAsState().value,
            onEvent = {viewModel.savedContact()})

        }

        composable(Routes.Home.route) {
            HomeScreen(
                navHostController = navHostController,
                state = state,
                viewModel = viewModel
            )
        }
    }

}