package com.example.mortgagecalculator.ui.theme

import android.icu.text.DecimalFormat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MortgageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MortgageState())
    val uiState: StateFlow<MortgageState> = _uiState.asStateFlow()

    fun setAmount(newAmount: Float) {
        if (newAmount >= 0) {
            _uiState.update { currentState ->
                currentState.copy(amount = newAmount)
            }
            uiState.value.amount = newAmount
        }
    }

    fun setYears (newYear: Int) {
        if (newYear >= 0) {
            _uiState.update { currentState ->
                currentState.copy(years = newYear)
            }
            uiState.value.years = newYear
        }
    }


    private fun monthlyPayment() {
        val mRate = uiState.value.rate / 12
        val temp = Math.pow((1/(1+mRate)).toDouble(), (uiState.value.years * 12).toDouble())
        _uiState.update { currentState ->
            currentState.copy(
                monthlyPayment = uiState.value.rate * mRate / (1 - temp).toFloat()
            )
        }
    }

    fun totalPayment() {
        _uiState.update { currentState ->
            currentState.copy(
                totalPayment = uiState.value.monthlyPayment * uiState.value.years * 12
            )
        }
    }
}

