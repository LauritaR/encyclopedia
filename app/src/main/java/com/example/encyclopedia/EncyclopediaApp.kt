package com.example.encyclopedia

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.presentation.components.BottomBar
import com.example.encyclopedia.presentation.components.TopBar
import com.example.encyclopedia.presentation.navigation.AppNavGraph
import com.example.encyclopedia.ui.theme.EncyclopediaTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@SuppressLint("ContextCastToActivity")
@Composable
fun EncyclopediaApp(){
    EncyclopediaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color= MaterialTheme.colorScheme.background
        ) {
            val windowSizeClass = calculateWindowSizeClass( LocalContext.current as Activity)

            val navController = rememberNavController()
            Scaffold(
              topBar = {
                  TopBar()
              },
                bottomBar = {
                    if(windowSizeClass.widthSizeClass != WindowWidthSizeClass.Expanded){
                        BottomBar(navController)
                    }

                }
            ) { innerPadding ->
                AppNavGraph(
                    navController = navController,
                    modifier= Modifier.padding(innerPadding)
                ) }
        }
    }
}
@Preview()
@Composable
fun AppPrev(){
    EncyclopediaApp()
}