package com.example.encyclopedia.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Main60

@Composable
fun OptionRow(option: String,isSelected:Boolean, onSelected: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .clickable { onSelected() }
    ){
        Checkbox(
            checked = isSelected,
            onCheckedChange = {onSelected()},
            colors = CheckboxDefaults.colors(
                checkmarkColor = Main60,
                uncheckedColor = Color.White,
                checkedColor = Accent10,
                disabledCheckedColor = Color.LightGray,
                disabledUncheckedColor = Color.Gray
            ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text=option,
            style = MaterialTheme.typography.bodyMedium,
            color = Accent10)
    }
}