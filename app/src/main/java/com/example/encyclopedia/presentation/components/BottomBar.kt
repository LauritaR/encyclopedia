package com.example.encyclopedia.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBar(navHostController: NavHostController){
   NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {

   }
}

@Preview()
@Composable
fun BottomBarPrev(){
    val navController = rememberNavController()
    BottomBar(navController)
}