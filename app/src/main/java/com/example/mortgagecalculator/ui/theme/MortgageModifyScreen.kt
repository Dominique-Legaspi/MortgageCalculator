package com.example.mortgagecalculator.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MortgageModifyScreen(
    onDoneClicked: () -> Unit,
    mortgageViewModel: MortgageViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val mortgageUiState by mortgageViewModel.uiState.collectAsState()
   var temp_amount by remember { mutableStateOf(mortgageViewModel.getAmount().toString())}
    var temp_rate  by remember { mutableStateOf(mortgageViewModel.getRate().toString())}

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
            YearSelection(mortgageViewModel)
        }
        Row (modifier = Modifier.padding(start = 14.dp, 2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Amount",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(1f)
            )
            OutlinedTextField(
                value = temp_amount,
                onValueChange = {temp_amount = it

                                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.weight(1f).padding(end = 5.dp))
        }
        Row (modifier = Modifier.padding(start = 14.dp, 2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Interest Rate",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(1f)
            )
            OutlinedTextField(
                value = temp_rate,
                onValueChange = {temp_rate = it

                                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.weight(1f).padding(end = 5.dp))
        }
        Button(onClick = {
            mortgageViewModel.setAmount(temp_amount.toFloat())
            mortgageViewModel.setRate(temp_rate.toFloat())
            onDoneClicked()},
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)) {
                Text(text = "Done")
        }
    }
}


@Composable
fun YearSelection(mortgageViewModel: MortgageViewModel = MortgageViewModel()) {
    var selected = 0
    when(mortgageViewModel.getYear()){
        10 -> selected = 0
        15 -> selected = 1
        30 -> selected = 2
        else -> selected = 1
    }

    val radioOptions = listOf("10", "15", "30")
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[selected]) }

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
                onClick = { onOptionSelected(text)
                            mortgageViewModel.setYears(text.toInt())}
            )
            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        label = { Text(stringResource(label))},
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = keyboardOptions
    )
}