package com.example.gridapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults

// Title composable
@Composable
fun AppTitle(text: String) {
    Text(
        text = text,
        fontSize = 28.sp,
        color = Color(0xFFF6F6F6),
        modifier = Modifier.padding(16.dp)
    )
}

// TextField composable
@Composable
fun AppTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        singleLine = true,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors( // <-- THE FIX IS HERE
            // Parameter names for text color are also more specific in M3
            focusedTextColor = Color.Gray,
            unfocusedTextColor = Color.Gray,

            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.Gray,

            // 'placeholderColor' is now 'unfocusedPlaceholderColor'
            unfocusedPlaceholderColor = Color.LightGray,
            focusedPlaceholderColor = Color.LightGray
        )
    )
}

// Button composable
@Composable
fun AppButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text)
    }
}

// Grid item composable
@Composable
fun GridItem(row: Int, column: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color(0xFF6200EE), RoundedCornerShape(4.dp))
            .size(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Item [$row][$column]", color = Color.White, fontSize = 14.sp)
    }
}
