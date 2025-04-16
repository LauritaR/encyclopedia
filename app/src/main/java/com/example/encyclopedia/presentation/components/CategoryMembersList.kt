package com.example.encyclopedia.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.rememberAsyncImagePainter
import com.example.encyclopedia.presentation.viewmodel.CategoryMember

@Composable
fun CategoryMembersList(members: List<CategoryMember>) {
    LazyColumn {
        items(members, key = { it.pageid }) { member ->
            Column {
                Text(
                    text = member.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                val imagePainter = rememberAsyncImagePainter(
                    model = member.imageUrl,
                    onSuccess = { Log.d("ImageURL", "Using image URL: ${member.imageUrl}") },
                    onError = {
                        Log.e(
                            "Coil",
                            "Error loading image: ${it.result.throwable.message}"
                        )
                    }

                )
                Image(
                    painter = imagePainter,
                    contentDescription = "Image of ${member.title}",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = member.description ?: "No description available",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}