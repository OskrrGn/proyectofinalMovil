package com.example.proyectofinalmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { UIPrincipal() }
    }
}

@Composable
fun UIPrincipal() {
    val showDialog = remember { mutableStateOf(false) }
    val resultado = remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                showDialog.value = true
                resultado.value = null
                scope.launch {
                    // Simulando una tarea pesada
                    delay(3000)
                    resultado.value = "Finito..."
                    showDialog.value = false
                }
            }
        ) {
            Text("Iniciar SubProceso")
        }

        Spacer(modifier = Modifier.height(24.dp))

        resultado.value?.let {
            Text("Resultado: $it")
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { /* No hacer nada */ },
                title = { Text("Por favor aguanta...") },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(modifier = Modifier.size(26.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Recuperando Datos")
                    }
                },
                confirmButton = { /* No es necesario un botón de confirmación */ },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UIPrincipal()
}