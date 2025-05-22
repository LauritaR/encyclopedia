package com.example.encyclopedia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.R
import com.example.encyclopedia.presentation.theme.Main60
@Composable
fun BottomBar(navController: NavController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route ?: ""
    fun extractSimpleCategory(route: String): String? {
        return when {
            route.contains("Category:Mammal_common_names") -> "Mammals"
            route.contains("Category:Bird_common_names") -> "Birds"
            route.contains("Category:Reptile_common_names") -> "Reptiles"
            route.contains("Category:Fish_common_names") -> "Fish"
            else -> null
        }
    }

    val currentCategory = remember(currentRoute) {
        extractSimpleCategory(currentRoute)
    }

    NavigationBar(containerColor = Main60) {

        IconButton(
            onClick = {
                navController.navigate("homescreen") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.weight(0.5f).size(60.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }

        fun isSelected(category: String): Boolean = currentCategory == category

        fun navigateIfQuiz(category: String, listCategory: String) {
            if (currentRoute.startsWith("quizScreen/")) {
                navController.navigate("quizScreen/$category") {
                    popUpTo("homescreen") { inclusive = false }
                    launchSingleTop = true
                }
            } else {
                navController.navigate("listScreen/$listCategory")
            }
        }
        @Composable
        fun CategoryIconButton(
            category: String,
            listCategory: String,
            iconRes: Int,
            sizeDp: Int
        ) {
            val selected = isSelected(category)

            val backgroundColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            else MaterialTheme.colorScheme.surface

            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .size((sizeDp + if (selected) 10 else 0).dp) // slight size bump if selected
                    .background(
                        color = backgroundColor,
                        shape = CircleShape
                    )
                    .clickable { navigateIfQuiz(category, listCategory) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(sizeDp.dp)
                )
            }
        }

        CategoryIconButton(
            category = "Mammals",
            listCategory = "Category:Mammal_common_names",
            iconRes = R.drawable.deer,
            sizeDp = 50
        )

        CategoryIconButton(
            category = "Birds",
            listCategory = "Category:Bird_common_names",
            iconRes = R.drawable.bird,
            sizeDp = 40
        )

        CategoryIconButton(
            category = "Reptiles",
            listCategory = "Category:Reptile_common_names",
            iconRes = R.drawable.reptile,
            sizeDp = 50
        )

        CategoryIconButton(
            category = "Fish",
            listCategory = "Category:Fish_common_names",
            iconRes = R.drawable.fish,
            sizeDp = 50
        )
    }
}
