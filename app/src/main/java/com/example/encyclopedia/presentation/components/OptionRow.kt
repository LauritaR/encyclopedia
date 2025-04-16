package com.example.encyclopedia.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionRow(option: String,isSelected:Boolean, onSelected: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .clickable { onSelected() }
    ){
        Checkbox(checked = isSelected, onCheckedChange = {onSelected()})
        Spacer(modifier = Modifier.width(8.dp))
        Text(text=option, style = MaterialTheme.typography.bodyMedium)
    }
}