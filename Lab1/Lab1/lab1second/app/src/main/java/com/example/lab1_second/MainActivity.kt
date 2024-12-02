package com.example.lab1_second

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1_second.ui.theme.Lab1secondTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1secondTheme {
                MessageApp()
            }
        }
    }
}

@Composable
fun MessageApp() {
    // State to store user input and message
    var userInput by remember { mutableStateOf("") }
    var displayedMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Image
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Android Logo",
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Instruction Text
        Text(
            text = "Please enter your message below:",
            style = TextStyle(fontSize = 16.sp),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Field
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            placeholder = { Text("Type your message here") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    userInput = ""
                    displayedMessage = ""
                }
            ) {
                Text(text = "Clear")
            }

            Button(
                onClick = {
                    displayedMessage = userInput
                }
            ) {
                Text(text = "Validate")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Message
        if (displayedMessage.isNotEmpty()) {
            Text(
                text = "You have entered the following message:",
                style = TextStyle(fontSize = 14.sp, color = Color.Red)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = displayedMessage,
                style = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
        }
    }
}
