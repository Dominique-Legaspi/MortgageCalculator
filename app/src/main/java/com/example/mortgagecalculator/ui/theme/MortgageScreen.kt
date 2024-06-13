package com.example.mortgagecalculator.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MortgageScreen(
    mortgageViewModel: MortgageViewModel = viewModel()
) {
    val mortgageUiState by mortgageViewModel.uiState.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        RowInfo(name = "Amount", data = mortgageUiState.amount)
        RowInfo(name = "Year", data = mortgageUiState.years)
        RowInfo(name = "Interest Rate", data = mortgageUiState.rate)
        RowInfo(name = "Monthly Payment", data = mortgageUiState.monthlyPayment)
        RowInfo(name = "Total Payment", data = mortgageUiState.totalPayment)
    }
}


@Preview(showBackground = true)
@Composable
fun MortgagePreview() {
    MortgageScreen()
}
