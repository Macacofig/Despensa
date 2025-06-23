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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.ucb.despensa.service.util

// Colores personalizados
val fondoGeneral = Color(0xFFB2EBF2)
val fondoTarjeta = Color(0xFFB2DFDB)
val textoPrincipal = Color(0xFF004D40)
val fondoBoton = Color(0xFF00695C)
val textoTarjeta = Color(0xFF000000)

@Composable
fun EditarUI(
    nombre: String,
    password: String,
    viewModel: EditarViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state = viewModel.stateE.collectAsState()
    val context = LocalContext.current
    // Llamamos cargarProductos una sola vez al montar la UI
    LaunchedEffect(Unit) {
        viewModel.inicializar(nombre, password)
    }
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
                text = "Actualice la cantidad en stock del producto ",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            when (val currentState = state.value) {
                is EditarViewModel.ProductosStateE.MostrarE -> {
                    val productos = currentState.productos
                    productos.forEach { producto ->
                        var cantidad by remember { mutableStateOf(producto.cantidad.toString()) }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = fondoTarjeta),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Nombre: ${producto.nombreProducto}",
                                    fontWeight = FontWeight.Bold,
                                    color = textoTarjeta
                                )
                                Text(
                                    text = "Código: ${producto.codigoProducto}",
                                    fontWeight = FontWeight.Medium,
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

                                Spacer(modifier = Modifier.height(12.dp))

                                Button(
                                    onClick = {
                                        val nuevaCantidad = cantidad.toIntOrNull() ?: producto.cantidad
                                        viewModel.actualizarCantidad(producto.codigoProducto, nuevaCantidad)
                                        util.sendNotificatión(context, "Producto actualizado exitosamente.")
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

                is EditarViewModel.ProductosStateE.NohayProductosE -> {
                    Text("No hay productos para mostrar", color = textoPrincipal)
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
