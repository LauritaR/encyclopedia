package com.example.encyclopedia.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Main60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String = "Encyclopedia"){
    TopAppBar(
        title={
            Text(text =  title)
        },
        navigationIcon={},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Main60,
            titleContentColor = Accent10
        )
        )
}

@Preview()
@Composable
fun TopBarPrev(){
    TopBar("Encyclopedia")
}