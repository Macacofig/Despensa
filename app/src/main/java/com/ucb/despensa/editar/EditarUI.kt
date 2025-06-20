package com.ucb.despensa.editar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Colores personalizados
val fondoGeneral = Color(0xFFB2EBF2)
val fondoTarjeta = Color(0xFFB2DFDB)
val textoPrincipal = Color(0xFF004D40)
val fondoBoton = Color(0xFF00695C)
val textoTarjeta = Color(0xFF000000)

@Composable
fun EditarUI(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGeneral)
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "EDITAR PRODUCTOS",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = MaterialTheme.typography.headlineLarge.fontSize),
                fontWeight = FontWeight.Bold,
                color = textoPrincipal,
                fontSize = 30.sp,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            Text(
                text = "Edite solo la cantidad o fecha de vencimiento:",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Lista de productos visuales
            repeat(2) { index ->
                val nombre = "Producto ${index + 1}"
                var cantidad by remember { mutableStateOf(TextFieldValue("")) }
                var fecha by remember { mutableStateOf(TextFieldValue("")) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = fondoTarjeta),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Nombre: $nombre",
                            fontWeight = FontWeight.Bold,
                            color = textoTarjeta
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = cantidad,
                            onValueChange = { cantidad = it },
                            label = { Text("Cantidad") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            label = { Text("Fecha de Vencimiento") },
                            placeholder = { Text("dd/mm/aaaa") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                // Visual: no acción real
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = fondoBoton),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Guardar Cambios", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun EditarPreview() {
//    EditarUI(navController = rememberNavController())
//}