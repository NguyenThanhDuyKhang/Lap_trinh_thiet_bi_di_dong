package com.example.smarttask.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smarttask.navigation.AppRoutes
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyCodeScreen(navController: NavController, email: String) {
    var otpValue by remember { mutableStateOf(List(6) { "" }) }
    val focusRequesters = remember { List(6) { FocusRequester() } }
    val isButtonEnabled = remember(otpValue) { otpValue.joinToString("").length == 6 }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                AuthHeader()
                Spacer(modifier = Modifier.height(48.dp))

                Text(
                    text = "Verify Code",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Enter the the code we just sent you on your registered Email",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 0..5) {
                        OtpCell(
                            value = otpValue[i],
                            onValueChange = { newValue ->
                                if (newValue.all { it.isDigit() } && newValue.length <= 1) {
                                    val newList = otpValue.toMutableList()
                                    newList[i] = newValue
                                    otpValue = newList
                                    if (newValue.isNotEmpty() && i < 5) {
                                        focusRequesters[i + 1].requestFocus()
                                    }
                                }
                            },
                            focusRequester = focusRequesters[i],
                            onBackspace = {
                                if (i > 0) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                            }
                        )
                    }
                }

                LaunchedEffect(Unit) {
                    delay(300)
                    focusRequesters[0].requestFocus()
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navController.navigate(AppRoutes.RESET_PASSWORD.replace("{email}", email))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled = isButtonEnabled
                ) {
                    Text("Next", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun OtpCell(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onBackspace: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .size(50.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Backspace && value.isEmpty()) {
                    onBackspace()
                    true
                } else {
                    false
                }
            },
        textStyle = LocalTextStyle.current.copy(
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}