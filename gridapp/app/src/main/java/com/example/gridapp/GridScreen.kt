package com.example.gridapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gridapp.ui.theme.GridItem

@Composable
fun GridScreen(rows: Int, columns: Int) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(rows) { row ->
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                items(columns) { column ->
                    GridItem(row, column)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
