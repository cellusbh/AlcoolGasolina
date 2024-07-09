package com.example.alcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcoolougasolina.ui.theme.AlcoolOuGasolinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlcoolOuGasolinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    var valorGasolina by remember {
        mutableStateOf("")
    }

    var valorAlcool by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .background(color = Color(0xFF03A9F4))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Álcool ou Gasolina?",
            style = TextStyle(
                color = Color.Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.size(16.dp))

        AnimatedVisibility(visible = valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
            if (valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {

                var ehGasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7

                val alcoolOuGasolina = if (ehGasolina) "Gasolina" else "Álcool"
                val cor = if (ehGasolina) Color.Red else Color.Green

                Text(
                    text = alcoolOuGasolina,
                    style = TextStyle(
                        color = cor,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 5f
                        )
                    )
                )
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        TextField(
            value = valorGasolina,
            onValueChange = { valorGasolina = it.filter { char -> char.isDigit() } },
            label = {
                Text("Gasolina")
            }
        )

        Spacer(modifier = Modifier.size(8.dp))

        TextField(
            value = valorAlcool,
            onValueChange = { valorAlcool = it.filter { char -> char.isDigit() } },
            label = {
                Text("Álcool")
            }
        )
    }
}

@Preview
@Composable
fun AppPreview() {
    AlcoolOuGasolinaTheme {
        App()
    }
}