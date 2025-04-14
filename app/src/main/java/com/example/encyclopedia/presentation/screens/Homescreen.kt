package com.example.encyclopedia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.encyclopedia.presentation.components.NewSearchBar
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Secondary15
import com.example.encyclopedia.presentation.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homescreen(modifier:Modifier = Modifier){
    val viewModel:AppViewModel = viewModel()
   Box(
       modifier = Modifier
           .fillMaxSize()
           .clickable {  }
           .background(Accent10)
   ){
       Column(modifier= Modifier
           .fillMaxHeight()
           .padding(16.dp),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally) {
           Spacer(modifier=Modifier.padding(32.dp))
           Card(
               shape = RoundedCornerShape(30.dp),
               modifier = Modifier.padding(16.dp),
               elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
           ) {
               Text(
                   text = "Encyclopedia",
                   fontSize = 35.sp,
                   modifier = Modifier
                       .background(Secondary15)
                       .padding(16.dp),
                   color = Accent10
               )
           }
           //searchbar
           Card(
               shape = RoundedCornerShape(30.dp),
               modifier = Modifier
                   .padding(8.dp),
               elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
               colors = CardDefaults.cardColors(containerColor = Accent10)
           ) {
               Box(
                   modifier= Modifier
                       .fillMaxWidth()
                       .padding(16.dp)
                       .background(Accent10, RoundedCornerShape(30.dp)),
               ){
                   NewSearchBar(viewModel)

               }
           }

       }
   }

}

@Preview()
@Composable
fun HomescreenPrev(){
    Homescreen()
}