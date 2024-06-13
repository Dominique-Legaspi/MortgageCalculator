package com.example.mortgagecalculator.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mortgagecalculator.R

@Composable
fun MortgageModifyScreen(
    onDoneClicked: () -> Unit,
    mortgageViewModel: MortgageViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val mortgageUiState by mortgageViewModel.uiState.collectAsState()
    var newAmount by remember { mutableStateOf("") }

    Column (modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
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
        Button(onClick = onDoneClicked,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)) {
                Text(text = "Done")
        }
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