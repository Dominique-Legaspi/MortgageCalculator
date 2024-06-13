package com.example.mortgagecalculator.ui.theme

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowInfo(name: String, data: Any, modifier: Modifier = Modifier) {
    Row (modifier = modifier.padding(6.dp)){
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        )
        Text(
            text = data.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(1f)
        )
    }
}