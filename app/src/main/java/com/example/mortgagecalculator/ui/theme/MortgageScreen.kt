package com.example.mortgagecalculator.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mortgagecalculator.R

enum class MortgageScreen(@StringRes val title: Int) {
    Start(title = R.string.mortgage),
    Modify(title = R.string.modify_data)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortgageAppBar(
    currentScreen: MortgageScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun MortgageApp(
    mortgageViewModel: MortgageViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = MortgageScreen.valueOf(
        backStackEntry?.destination?.route ?: MortgageScreen.Start.name
    )

    Scaffold (
        topBar = {
            MortgageAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){ innerPadding ->
        val mortgageUiState by mortgageViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MortgageScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            ) {
            composable(route = MortgageScreen.Start.name) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top
                ) {
                    RowInfo(name = "Amount", data = mortgageUiState.amount)
                    RowInfo(name = "Year", data = mortgageUiState.years)
                    RowInfo(name = "Interest Rate", data = mortgageUiState.rate)
                    RowInfo(name = "Monthly Payment", data = mortgageUiState.monthlyPayment)
                    RowInfo(name = "Total Payment", data = mortgageUiState.totalPayment)
                    Button(
                        onClick = { navController.navigate(MortgageScreen.Modify.name)},
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally)) {
                        Text(text = "Modify Data")
                    }
                }
            }
            composable(route = MortgageScreen.Modify.name) {
                MortgageModifyScreen(onDoneClicked = { navController.navigate(MortgageScreen.Start.name)})
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MortgagePreview() {
    MortgageApp()
}
