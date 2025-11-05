package com.example.gridapp

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gridapp.ui.theme.*

@Composable
fun InsertRowsScreen(navController: NavController) {
    var rows by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        AppTitle("Insert Rows")
        AppTextField(
            value = rows,
            onValueChange = { rows = it },
            placeholder = "Enter number of rows"
        )
        AppButton(text = "Next") {
            if (rows.isNotEmpty())
                navController.navigate("columns/$rows")
        }
    }
}
