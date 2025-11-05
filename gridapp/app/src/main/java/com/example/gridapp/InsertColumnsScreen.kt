package com.example.gridapp

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gridapp.ui.theme.*

@Composable
fun InsertColumnsScreen(navController: NavController, rows: Int) {
    var columns by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        AppTitle("Insert Columns")
        AppTextField(
            value = columns,
            onValueChange = { columns = it },
            placeholder = "Enter number of columns"
        )
        AppButton(text = "Show Grid") {
            if (columns.isNotEmpty())
                navController.navigate("grid/$rows/${columns}")
        }
    }
}
