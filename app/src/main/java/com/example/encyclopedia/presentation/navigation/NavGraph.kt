package com.example.encyclopedia.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.presentation.screens.Homescreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController(),
                modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = "Homescreen"
    ) {
        composable("Homescreen"){ Homescreen() }
    }
}