package com.example.encyclopedia.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Accent5
import com.example.encyclopedia.presentation.theme.Main60
import com.example.encyclopedia.presentation.theme.Secondary15
import com.example.encyclopedia.presentation.viewmodel.ApiViewModel
import com.example.encyclopedia.presentation.viewmodel.CategoryMember

@Composable
fun Listscreen(
    categoryTitle: String,
    apiViewModel: ApiViewModel = viewModel(),
    onNavigateToInfo: (String, Int) -> Unit,
    navController:NavController
){
    LaunchedEffect(categoryTitle) {
        if (apiViewModel.categoryMembers.isEmpty()) {
            apiViewModel.fetchCategoryMembers(categoryTitle)
            Log.d("CategoryMembers", "Fetched members: ${apiViewModel.categoryMembers.size}")
        }

    }
    val isLoading=apiViewModel.isLoading.value

    val categoryMembers = apiViewModel.categoryMembers

    val categoryNameMap = mapOf(
        "Category:Mammal_common_names" to "Mammals",
        "Category:Bird_common_names" to "Birds",
        "Category:Reptile_common_names" to "Reptiles",
        "Category:Fish_common_names" to "Fish"
    )
    val friendlyCategoryName = categoryNameMap[categoryTitle] ?: categoryTitle
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier=Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Main60),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Spacer(modifier = Modifier.size(70.dp))
            Text(
                text = "List of $friendlyCategoryName",
                modifier = Modifier
                    .padding(top = 50.dp, start = 40.dp, end = 40.dp, bottom = 30.dp)
                    .fillMaxWidth()
                    .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                    .background(Accent10, RoundedCornerShape(30.dp))
                    .padding(8.dp),
                color = Main60,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight(weight = 600)
            )
            Spacer(modifier = Modifier.weight(1f))
            if(isLoading){
                Box( modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()){
                    CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
                }
            }
            else{
                CategoryMembersList2(
                    members = categoryMembers,
                    categoryTitle = categoryTitle,
                    onNavigateToInfo = onNavigateToInfo)
            }

        }

    }

}

@Composable
fun CategoryMembersList2(members: List<CategoryMember>,
                         categoryTitle: String,
                         onNavigateToInfo: (String,Int) -> Unit)
{
    LazyColumn (modifier=Modifier
        .fillMaxHeight()
        .padding(bottom = 120.dp)
    ) {
        itemsIndexed(members) { index, member ->
            AnimalCard(
                animal = member,
                categoryTitle=categoryTitle,
                index=index,
                onNavigateToInfo = onNavigateToInfo)
        }
    }
}

@Composable
fun AnimalCard(
    animal: CategoryMember,
    categoryTitle:String,
    index:Int,
    onNavigateToInfo:(String, Int)->Unit,
    modifier: Modifier= Modifier){

    Card(modifier=modifier
        .width(350.dp)
        .height(100.dp)
        .padding(8.dp)
        .shadow(8.dp, shape = RoundedCornerShape(16.dp))
        .clickable { onNavigateToInfo(categoryTitle, index)},
    )
    {
        Box(
            modifier=Modifier.background(Accent10)
        )
        {
            Row(
                modifier=Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = rememberAsyncImagePainter(animal.imageUrl),
                    contentDescription = "Animal image",
                    modifier=Modifier.size(60.dp),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ){
                    Text(text = animal.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Main60,
                        fontWeight = FontWeight(weight = 800),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

