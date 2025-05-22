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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.presentation.components.BottomBar
import com.example.encyclopedia.presentation.components.NavRail
import com.example.encyclopedia.presentation.components.NavType
import com.example.encyclopedia.presentation.components.TopBar
import com.example.encyclopedia.presentation.navigation.NavGraph
import com.example.encyclopedia.presentation.screens.Homescreen

import com.example.encyclopedia.presentation.theme.EncyclopediaTheme


@Composable
fun EncyclopediaApp(
    windowClass:WindowWidthSizeClass,
    appContext: Context,

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
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val shouldShowNav = currentRoute != "homescreen" &&
            currentRoute != "resultScreen/{score}/{totalQuestions}" &&
            currentRoute != "loginScreen" &&
            currentRoute != "registerScreen" &&
            currentRoute != "userResultsScreen/{userId}"

    EncyclopediaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color= MaterialTheme.colorScheme.background
        ) {
            Scaffold(
              topBar = {
                  if(shouldShowNav) {
                      TopBar()
                  }
              },
                bottomBar = {
                    if (navType == NavType.BOTTOM_NAV && shouldShowNav) {
                        BottomBar(navController = navController)
                    }
                }
            ) { innerPadding ->
                if (navType == NavType.NAV_RAIL) {
                    Row(modifier = Modifier.padding(innerPadding)) {
                        if(shouldShowNav) {
                            NavRail(navController = navController)
                        }
                        NavGraph(
                            applicationContext = appContext,
                            navController = navController
                        )
                    }
                } else {
                    NavGraph(
                        applicationContext = appContext,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun EncyclopediaAppPreview() {
    val context = LocalContext.current

    EncyclopediaApp(
        windowClass = WindowWidthSizeClass.Compact,
        appContext = context
    )
}

@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun EncyclopediaAppTablet() {
    val context = LocalContext.current

    EncyclopediaApp(
        windowClass = WindowWidthSizeClass.Medium,
        appContext = context
    )
}

@Preview(showBackground = true, device = "spec:width=1920dp,height=1080dp,dpi=160")
@Composable
fun EncyclopediaAppDesktop() {
    val context = LocalContext.current
    EncyclopediaApp(
        windowClass = WindowWidthSizeClass.Expanded,
        appContext = context
    )
}