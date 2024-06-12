package com.example.mortgagecalculator.ui.theme

import android.icu.text.DecimalFormat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MortgageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MortgageState())
    var selfAmount = 0.00f
    var selfYear = 0
    var selfRate = 0.000f
    val uiState: StateFlow<MortgageState> = _uiState.asStateFlow()
    val MONEY: DecimalFormat = DecimalFormat("$#, ##0.00")
    
    fun setAmount(newAmount: Float) {
        if (newAmount >= 0) {
            _uiState.update { currentState ->
                currentState.copy(amount = newAmount)
            }
            selfAmount = newAmount
        }
    }
    
    fun setYears (newYear: Int) {
        if (newYear >= 0) {
            _uiState.update { currentState ->
                currentState.copy(years = newYear)
            }
            selfYear = newYear
        }
    }
    
    fun monthlyPayment(): Float {
        val mRate = selfRate / 12
        val temp = Math.pow((1/(1+mRate)).toDouble(), (selfYear * 12).toDouble())

        return selfAmount * mRate / (1 - temp).toFloat()
    }

    fun totalPayment(): Float {
        return monthlyPayment() * selfYear * 12
    }

    fun formattedTotalPayments(): String? {
        return MONEY.format(totalPayment())
    }
}

