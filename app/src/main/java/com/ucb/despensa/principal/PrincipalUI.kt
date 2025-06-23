package com.ucb.despensa.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.navigation.screen
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

// Colores
val fondoGeneral = Color(0xFFB2EBF2)
val fondoTarjeta = Color(0xFFB2DFDB)
val textoPrincipal = Color(0xFF004D40)
val fondoBoton = Color(0xFF00695C)
val textoTarjeta = Color(0xFF000000)

@Composable
fun PrincipalUI(
    nombre: String,
    password: String,
    viewModel: PrincipalViewModel = hiltViewModel(),
    onAgregarClick: () -> Unit,
    onEditarClick: () -> Unit,
    onEliminarClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.inicializar(nombre, password)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGeneral)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MiDespensa",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                color = textoPrincipal,
                modifier = Modifier.padding(40.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                when (state) {
                    is PrincipalViewModel.PorductosState.Mostrar -> {
                        val productos = (state as PrincipalViewModel.PorductosState.Mostrar).productos
                        items(productos.size) { index ->
                            val producto = productos[index]
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = fondoTarjeta),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(text = "Nombre: ${producto.nombreProducto ?: "N/A"}", color = textoTarjeta)
                                    Text(text = "Código: ${producto.codigoProducto ?: "N/A"}", color = textoTarjeta)
                                    Text(text = "Cantidad: ${producto.cantidad ?: 0}", color = textoTarjeta)
                                }
                            }
                        }
                    }
                    PrincipalViewModel.PorductosState.NohayProductos -> {
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
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(fondoGeneral.copy(alpha = 0.95f))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onAgregarClick() },
                colors = ButtonDefaults.buttonColors(containerColor = fondoBoton)
            ) {
                Text("Agregar")
            }
            Button(
                onClick = { onEliminarClick() },
                colors = ButtonDefaults.buttonColors(containerColor = fondoBoton)
            ) {
                Text("Eliminar")
            }
            Button(
                onClick = { onEditarClick() },
                colors = ButtonDefaults.buttonColors(containerColor = fondoBoton)
            ) {
                Text("Editar")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PrincipalPreview() {
//    PrincipalUI(navController = rememberNavController())
//}