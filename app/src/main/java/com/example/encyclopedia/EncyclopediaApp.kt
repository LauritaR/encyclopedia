package com.example.encyclopedia


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.presentation.components.BottomBar
import com.example.encyclopedia.presentation.components.NavRail
import com.example.encyclopedia.presentation.components.NavType
import com.example.encyclopedia.presentation.components.TopBar
import com.example.encyclopedia.presentation.navigation.AppNavGraph
import com.example.encyclopedia.presentation.screens.Homescreen
import com.example.encyclopedia.presentation.theme.EncyclopediaTheme

@Composable
fun EncyclopediaApp(windowClass:WindowWidthSizeClass){
    val navController = rememberNavController()

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
            ) {innerPadding->
               if(navType == NavType.NAV_RAIL){
                   Row(modifier = Modifier.padding(innerPadding)) {
                       NavRail()
                       AppNavGraph(
                           navController=navController,
                           modifier = Modifier.weight(1f)
                       )
                      Homescreen()
                   }
               }
               else
               {
                   AppNavGraph(
                       navController=navController,
                       modifier = Modifier.padding(innerPadding)
                   )
                   Homescreen()
               }
            }

        }
    }
}
@Preview(name = "Compact Preview")
@Composable
fun CompactPreview() {
    EncyclopediaApp(windowClass = WindowWidthSizeClass.Compact)
}

@Preview(name = "Medium Preview", device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun MediumPreview() {
    EncyclopediaApp(windowClass = WindowWidthSizeClass.Medium)
}

@Preview(name = "Expanded Preview", device = "spec:width=1920dp,height=1080dp,dpi=160")
@Composable
fun ExpandedPreview() {
    EncyclopediaApp(windowClass = WindowWidthSizeClass.Expanded)
}