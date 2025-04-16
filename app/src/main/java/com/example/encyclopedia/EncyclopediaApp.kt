package com.example.encyclopedia

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.encyclopedia.presentation.components.BottomBar
import com.example.encyclopedia.presentation.components.NavRail
import com.example.encyclopedia.presentation.components.NavType
import com.example.encyclopedia.presentation.components.TopBar
import com.example.encyclopedia.presentation.navigation.NavGraph

import com.example.encyclopedia.presentation.theme.EncyclopediaTheme


@Composable
fun EncyclopediaApp(
    windowClass:WindowWidthSizeClass,
    appContext: Context
){
    val navType:NavType = when(windowClass){
        WindowWidthSizeClass.Compact -> {
            NavType.BOTTOM_NAV
        }
        WindowWidthSizeClass.Medium -> {
            NavType.NAV_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            NavType.NAV_RAIL
        }
        else -> {
            NavType.BOTTOM_NAV
        }
    }
    EncyclopediaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color= MaterialTheme.colorScheme.background
        ) {
            Scaffold(
              topBar = {
                  TopBar()
              },
                bottomBar = {
                    if (navType == NavType.BOTTOM_NAV) {
                        BottomBar()
                    }
                }
            ) { innerPadding ->
                if (navType == NavType.NAV_RAIL) {
                    Row(modifier = Modifier.padding(innerPadding)) {
                        NavRail()
                        NavGraph(
                            applicationContext = appContext
                        )
                    }
                } else {
                    NavGraph(
                        applicationContext = appContext
                    )
                }
            }
        }
    }
}
