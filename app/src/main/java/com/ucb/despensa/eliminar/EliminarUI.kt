package com.ucb.despensa.eliminar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
//import androidx.navigation.compose.rememberNavController


//colores
//val fondoGeneral = Color(0xFFB2EBF2)
val fondoTarjeta = Color(0xFFB2DFDB)
val textoPrincipal = Color(0xFF004D40)
//val fondoBoton = Color(0xFF00695C)
val textoTarjeta = Color(0xFF000000)

@Composable
fun EliminarUI(
    onBackClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Eliminar Productos",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier.padding(40.dp),
                color = textoPrincipal
            )

            Text(
                text = "Elimine el Producto que ya no desee que este en su inventario:",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(2) { index ->
                    val nombre = "Producto ${index + 1}"
                    val cantidad = (5 - index) * 2
                    val fecha = "01/07/2025"

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(fondoTarjeta, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text("Nombre: $nombre", fontWeight = FontWeight.Bold, color = textoTarjeta)
                            Text("Cantidad: $cantidad", color = textoTarjeta)
                            Text("Vence: $fecha", color = textoTarjeta)
                        }

                        IconButton(
                            onClick = { /* Visual, sin acción */ },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = (-8).dp, y = (8).dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {/*
                    navController.navigate(screen.ProductosScreen.route) {
                        popUpTo(screen.EliminarScreen.route) { inclusive = true }
                    }*/
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Volver a Productos", color = Color.White)
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun EliminarPreview() {
//    EliminarUI(/*navController = rememberNavController()*/)
//}
