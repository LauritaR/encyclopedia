package com.example.encyclopedia.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Main60
import com.example.encyclopedia.presentation.theme.Secondary15
import com.example.encyclopedia.presentation.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewSearchBar(viewModel: AppViewModel) {
    val searchText by viewModel.searchText.collectAsState()
    val isExpanded by viewModel.isSearching.collectAsState()

    SearchBar(
        inputField = {
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                placeholder = { Text("Search a category") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                        focusedContainerColor = Accent10,
                        unfocusedContainerColor = Accent10,
                        disabledContainerColor = Accent10,
                        cursorColor = Main60,
                        focusedTextColor = Main60,
                        unfocusedTextColor = Main60
                    )
                )
        },
        expanded = isExpanded,
        onExpandedChange = { viewModel.onToggleSearch() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(30.dp),
        tonalElevation = 0.dp,
        colors = SearchBarDefaults.colors(
            containerColor = Main60
        ),
        content = {
            val searchResults by viewModel.animalCatList.collectAsState()
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(searchResults) { category ->
                    Text(
                        text = category.categoryName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {  }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SBPreview(){
    val viewModel :AppViewModel = viewModel()
    NewSearchBar(viewModel)
}