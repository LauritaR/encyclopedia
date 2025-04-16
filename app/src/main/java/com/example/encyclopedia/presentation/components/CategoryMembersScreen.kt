package com.example.encyclopedia.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.encyclopedia.presentation.viewmodel.ApiViewModel

@Composable
fun CategoryMembersScreen(categoryTitle: String) {
    val apiViewModel: ApiViewModel = viewModel()
    val categoryMembers = apiViewModel.categoryMembers

    LaunchedEffect(categoryTitle) {
        apiViewModel.fetchCategoryMembers(categoryTitle)
    }
    CategoryMembersList(members = categoryMembers)
}