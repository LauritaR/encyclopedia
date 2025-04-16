package com.example.encyclopedia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.encyclopedia.data.local.AppData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AppViewModel : ViewModel() {
    private val _isSearching = MutableStateFlow(false)
    val isSearching  = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val animalCategories = AppData().loadAnimals()
    private val _animalCatList = MutableStateFlow(animalCategories)
    val animalCatList = searchText
        .combine(_animalCatList){ text, animalCategories ->
            if(text.isBlank()){
                animalCategories
            }
            else
            {
                animalCategories.filter { category ->
                    category.categoryName.uppercase().contains(text.trim().uppercase())
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _animalCatList.value
        )


    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    fun onToggleSearch(){
        _isSearching.value = !_isSearching.value
        if(!_isSearching.value){
            this.onSearchTextChange("")
        }
    }
}