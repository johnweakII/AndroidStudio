package com.example.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "rows") {
        composable("rows") {
            InsertRowsScreen(navController)
        }
        composable("columns/{rows}") { backStack ->
            val rows = backStack.arguments?.getString("rows")?.toIntOrNull() ?: 0
            InsertColumnsScreen(navController, rows)
        }
        composable("grid/{rows}/{cols}") { backStack ->
            val rows = backStack.arguments?.getString("rows")?.toIntOrNull() ?: 0
            val cols = backStack.arguments?.getString("cols")?.toIntOrNull() ?: 0
            GridScreen(rows, cols)
        }
    }
}
