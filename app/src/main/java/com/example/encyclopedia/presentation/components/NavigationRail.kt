package com.example.encyclopedia.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.R

data class NavigationItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon:ImageVector,
    val hasNews:Boolean,
    val badgeCount:Int? = null
)

@Composable
fun NavRail(navController: NavController
){
//    val items = listOf(
//    NavigationItem("Mammals", Icons.Outlined.Home, Icons.Filled.Home, hasNews = false),
//    NavigationItem("Birds", Icons.Outlined.Add, Icons.Filled.Add, hasNews = true, badgeCount = 2)
//)

    NavigationRail {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
        IconButton(onClick = { navController.navigate("listScreen/Category:Mammal_common_names") }) {
            Icon(painter = painterResource(R.drawable.deer), contentDescription = "Mammals")
        }
        IconButton(onClick = { navController.navigate("listScreen/Category:Bird_common_names") }) {
            Icon(painter = painterResource(R.drawable.bird), contentDescription = "Birds")
        }
        IconButton(onClick = { navController.navigate("listScreen/Category:Reptile_common_names") }) {
            Icon(painter = painterResource(R.drawable.reptile), contentDescription = "Reptiles")
        }
        IconButton(onClick = { navController.navigate("listScreen/Category:Fish_common_names") }) {
            Icon(painter = painterResource(R.drawable.fish), contentDescription = "Fish")
        }

//        items.forEach { item ->
//            NavigationRailItem(
//                selected = false,
//                onClick = { /* handle click */ },
//                icon = {
//                    if (item.badgeCount != null) {
//                        BadgedBox(badge = { Badge { Text(item.badgeCount.toString()) } }) {
//                            Icon(imageVector = item.unselectedIcon, contentDescription = item.title)
//                        }
//                    } else {
//                        Icon(imageVector = item.unselectedIcon, contentDescription = item.title)
//                    }
//                },
//                label = { Text(item.title) },
//                alwaysShowLabel = false
//            )
//        }
    }
}

@Preview()
@Composable
fun NavRailPrev(){
    val navController = rememberNavController()
    NavRail(navController=navController)
}