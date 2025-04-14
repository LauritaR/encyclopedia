package com.example.encyclopedia.presentation.components

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.Navigation

data class NavigationItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon:ImageVector,
    val hasNews:Boolean,
    val badgeCount:Int? = null
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun NavRail(
){
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        NavigationItem(
            title = "Fish",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = true,
            badgeCount = 3
        ),
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
    )
    val context = LocalContext.current
    val activity = context as? Activity
    val windowClass = activity?.let { calculateWindowSizeClass(it) }

    NavigationRail {
        items.forEach{item->
            NavigationRailItem(
                selected = false,
                onClick= {},
                icon ={
                    if(item.badgeCount != null){
                        BadgedBox(badge= {
                            Badge{
                                Text(item.badgeCount.toString())
                        }
                        }) {
                            Icon(imageVector = item.unselectedIcon, contentDescription = item.title)
                        }
                    }else{
                        Icon(imageVector = item.unselectedIcon, contentDescription = item.title)
                    }
                },
                label={Text(item.title)},
                alwaysShowLabel = false
            )
        }
    }
}

@Preview()
@Composable
fun NavRailPrev(){
    NavRail()
}