package com.example.encyclopedia.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.presentation.theme.Main60

@Composable
fun BottomBar(){
   NavigationBar(containerColor = Main60) {

   }
}

@Preview()
@Composable
fun BottomBarPrev(){
    val navController = rememberNavController()
    BottomBar()
}