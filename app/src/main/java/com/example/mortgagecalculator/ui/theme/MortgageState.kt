package com.example.mortgagecalculator.ui.theme

data class MortgageState(
    var amount: Float = 100000.00f,
    var years: Int = 30,
    var rate: Float = 0.035f,
    var monthlyPayment: Float = 0.00f,
    var totalPayment: Float = 0.00f
)
