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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.encyclopedia.presentation.theme.Main60
import com.example.encyclopedia.presentation.theme.Secondary15

import com.example.encyclopedia.presentation.viewmodel.AppViewModel

@Composable
fun Homescreen(onNavigateToInfo:(String)->Unit,
               onNavigateToQuiz: (String)->Unit
){
    val viewModel:AppViewModel = viewModel()
   Box(
       modifier = Modifier
           .fillMaxSize()
           .clickable { }
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
                       .background(Main60)
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
            Box(
                contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Or choose a category",
                        modifier=Modifier
                            .padding(8.dp),
                        fontSize = 20.sp
                    )
                    Button(
                        onClick = {onNavigateToInfo("Category:Mammal_common_names")},
                        modifier= Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Main60,
                            contentColor = Secondary15
                        )
                    ) {
                        Text(
                            text = "Mammals"
                        )
                    }
                    Button(
                        onClick = {onNavigateToInfo("Category:Bird_common_names")},
                        modifier= Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Main60,
                            contentColor = Secondary15)

                    ) {
                        Text(
                            text = "Birds"
                        )
                    }
                    Button(
                        onClick = {onNavigateToInfo("Category:Reptile_common_names")},
                        modifier= Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Main60,
                            contentColor = Secondary15)
                    ) {
                        Text(
                            text = "Reptiles"
                        )
                    }
                    Button(
                        onClick = {onNavigateToInfo("Category:Fish_common_names")},
                        modifier= Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Main60,
                            contentColor = Secondary15)
                    ) {
                        Text(
                            text = "Aquatic Animals"
                        )
                    }
                }
            }
           Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
           ){
           Text(
               text = "Test your knowledge",
               fontSize = 20.sp
           )
           //button to navigate to quiz
           Button(
               onClick = {onNavigateToQuiz("Mammals")},
               modifier= Modifier
                   .height(90.dp)
                   .width(300.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = Main60,
                   contentColor = Secondary15)
           ) {
               Text(
                   text = "Ready, set, quiz!",
                   fontSize = 30.sp
               )
           }
       }
       }


   }
}


@Preview()
@Composable
fun HomescreenPrev(){
    Homescreen(
        onNavigateToInfo = {} ,
        onNavigateToQuiz = {  }
    )
}