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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


//colores
//val fondoGeneral = Color(0xFFB2EBF2)
val fondoTarjeta = Color(0xFFB2DFDB)
val textoPrincipal = Color(0xFF004D40)
//val fondoBoton = Color(0xFF00695C)
val textoTarjeta = Color(0xFF000000)

@Composable
fun EliminarUI(
    nombre: String,
    password: String,
    viewModel: EliminarViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.stateD.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.inicializar(nombre,password)
    }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                when (val currentState = state) {
                    is EliminarViewModel.PorductosStateD.MostrarD -> {
                        val productos = currentState.productos
                        items(productos.size) { index ->
                            val producto = productos[index]
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = fondoTarjeta),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(text = "Nombre: ${producto.nombreProducto}", color = textoTarjeta)
                                        Text(text = "Código: ${producto.codigoProducto}", color = textoTarjeta)
                                        Text(text = "Cantidad: ${producto.cantidad}", color = textoTarjeta)
                                    }

                                    IconButton(
                                        onClick = {
                                            viewModel.eliminarProducto(producto.codigoProducto)
                                        }
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
                    }

                    EliminarViewModel.PorductosStateD.NohayProductosD -> {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = fondoTarjeta),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("No hay productos disponibles", color = textoTarjeta)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {onBackClick()},
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
