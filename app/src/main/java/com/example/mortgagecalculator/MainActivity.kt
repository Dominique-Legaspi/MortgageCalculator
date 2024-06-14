package com.example.mortgagecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mortgagecalculator.ui.theme.MortgageApp
import com.example.mortgagecalculator.ui.theme.MortgageCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MortgageCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MortgageApp()
                }
            }
        }
    }
}