package com.ucb.despensa.agregar

import androidx.compose.foundation.background
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import android.widget.Toast
import com.ucb.despensa.service.util
import com.ucb.domain.Producto
//colores
val fondoGeneral = Color(0xFFB2EBF2)
//val fondoTarjeta = Color(0xFFB2DFDB)
val textoPrincipal = Color(0xFF004D40)
val fondoBoton = Color(0xFF00695C)
//val textoTarjeta = Color(0xFF000000)

@Composable
fun AgregarUI(
    nombre: String,
    password: String,
    onBackClick: () -> Unit,
    viewModel: AgregarViewModel = hiltViewModel()
) {
    var nombreProducto by remember { mutableStateOf("") }
    var codigoProducto by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    val usuarioListo by viewModel.usuarioCargado.collectAsState()

    val estado by viewModel.estado.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.inicializar(nombre, password)
    }

    LaunchedEffect(estado) {
        when (estado) {
            is AgregarViewModel.AgregarState.ProductoGuardado -> {
                util.sendNotificatión(context, "Producto agregado exitosamente.")
                Toast.makeText(context, "Producto guardado", Toast.LENGTH_SHORT).show()
                nombreProducto = ""
                codigoProducto = ""
                cantidad = ""
                viewModel.reiniciarEstado()
            }
            is AgregarViewModel.AgregarState.ProductoYaExiste -> {
                Toast.makeText(context, "El producto ya existe.", Toast.LENGTH_SHORT).show()
                viewModel.reiniciarEstado()
            }
            is AgregarViewModel.AgregarState.Error -> {
                Toast.makeText(context, "Ocurrió un error al guardar.", Toast.LENGTH_SHORT).show()
                viewModel.reiniciarEstado()
            }
            else -> {}
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGeneral)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón Back arriba a la izquierda
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = textoPrincipal
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "AGREGAR PRODUCTO",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = nombreProducto,
                onValueChange = { nombreProducto = it },
                label = { Text("Nombre del producto") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = codigoProducto,
                onValueChange = { codigoProducto = it },
                label = { Text("Código del producto") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it.filter { char -> char.isDigit() } }, // solo números
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val cantidadInt = cantidad.toIntOrNull() ?: 0
                    if (nombreProducto.isNotBlank() && codigoProducto.isNotBlank() && cantidadInt > 0) {
                        val producto = Producto(
                            nombreProducto = nombreProducto,
                            codigoProducto = codigoProducto,
                            cantidad = cantidadInt,
                            usuario_id = null
                        )
                        viewModel.guardarProd(producto)
//                        util.sendNotificatión(context, "Producto agregado exitosamente.")
//                        Toast.makeText(context, "Producto guardado", Toast.LENGTH_SHORT).show()
//                        // Limpiar campos si quieres:
//                        nombreProducto = ""
//                        codigoProducto = ""
//                        cantidad = ""
                    } else {
                        Toast.makeText(context, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = usuarioListo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = fondoBoton)
            ) {
                Text("Guardar", color = Color.White)
            }
        }
    }
}
