package com.example.encyclopedia.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import coil3.compose.rememberAsyncImagePainter
import com.example.encyclopedia.R
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Main30
import com.example.encyclopedia.presentation.theme.Main60
import com.example.encyclopedia.presentation.viewmodel.ApiViewModel
import com.example.encyclopedia.presentation.viewmodel.CategoryMember

@Composable
fun InfoScreen(
    categoryTitle:String,
    currentIndex:Int,
    apiViewModel: ApiViewModel = viewModel(),
    navController: NavController
)
{
    LaunchedEffect(categoryTitle) {
        if (apiViewModel.categoryMembers.isEmpty()) {
            apiViewModel.fetchCategoryMembers(categoryTitle)
        }
    }
    val categoryMembers=apiViewModel.categoryMembers

    val currentIndex=apiViewModel.currentIndex.value

    if(categoryMembers.isNotEmpty()){
        val categoryMember=categoryMembers[currentIndex]
        AnimalDescriptionCard(categoryMember=categoryMember,
            onPrevious ={apiViewModel.onClickedPrevious()},
            onNext={apiViewModel.onClickedNext()},
            isPreviousEnabled= currentIndex>0,
            isNextEnabled = currentIndex < categoryMembers.size - 1,
            navController = navController
        )
    }
}

@Composable
fun AnimalDescriptionCard(categoryMember: CategoryMember,
                          modifier: Modifier=Modifier,
                          onPrevious:()->Unit,
                          onNext:()-> Unit,
                          isPreviousEnabled:Boolean,
                          isNextEnabled:Boolean,
                          navController: NavController){
    Box(modifier=Modifier
        .fillMaxSize()
        .background(Main60)){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(32.dp)
                .fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement =  Arrangement.Top,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ){
                Spacer(modifier = Modifier.size(70.dp))
                Card(shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Accent10)

                ) {

                    Box(
                        modifier=Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ){
                        Text(
                            text = categoryMember.title,
                            modifier=Modifier
                                .align(Alignment.Center),
                            style= MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            color = Main60,
                            fontWeight = FontWeight(weight = 500)
                        )
                    }}

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    IconButton(
                        onClick = onPrevious,
                        enabled = isPreviousEnabled
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous"
                        )
                    }
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = categoryMember.imageUrl?: R.drawable.no_image
                        ),
                        contentDescription = categoryMember.title,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    )

                    IconButton(
                        onClick = onNext,
                        enabled = isNextEnabled
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next"
                        )
                    }

                }

                Card(modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Accent10)
                ) {

                    Text(
                        text = categoryMember.description?:"No description available",
                        modifier=Modifier.padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
