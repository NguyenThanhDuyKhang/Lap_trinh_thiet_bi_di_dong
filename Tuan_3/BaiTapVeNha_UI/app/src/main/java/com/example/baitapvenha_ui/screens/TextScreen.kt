package com.example.baitapvenha_ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun TextScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Text Detail") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("The ", style = MaterialTheme.typography.bodyLarge)
            Text("quick ", textDecoration = TextDecoration.LineThrough)
            Text("Brown ", color = Color(0xFF8B4513))
            Text("fox j u m p s over the lazy dog.", fontStyle = FontStyle.Italic)
        }
    }
}
