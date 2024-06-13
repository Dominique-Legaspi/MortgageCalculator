package com.example.mortgagecalculator.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MortgageModifyScreen(
    mortgageViewModel: MortgageViewModel = viewModel()
) {
    val mortgageUiState by mortgageViewModel.uiState.collectAsState()

    Column (
        Modifier.padding(top = 20.dp)
    ){
        Row (modifier = Modifier.padding(start = 14.dp, 2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Years",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(1f)
            )
            YearSelection()
        }
        RowInfo(
            name = "Amount",
            data = "$10000"
        )
        RowInfo(
            name = "Interest Rate",
            data = "3.5%"
        )
    }
}

@Composable
fun YearSelection() {
    val radioOptions = listOf("10", "15", "30")
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[1]) }

    radioOptions.forEach { text ->
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .selectable(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                    }
                )
        ) {
            RadioButton(
                selected = (text == selectedOption),
                onClick = { onOptionSelected(text)}
            )
            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Preview
@Composable
fun ModifyPreview() {
    MortgageModifyScreen()
}