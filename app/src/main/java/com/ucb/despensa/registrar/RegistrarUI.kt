package com.ucb.despensa.registrar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucb.despensa.navigation.screen

val fondoGeneral = Color(0xFFB2EBF2)
val textoPrincipal = Color(0xFF004D40)
val fondoBoton = Color(0xFF00695C)

@Composable
fun RegistrarUI(
    navController: NavController,
    onBackClick: () -> Unit,
    viewModel: RegistrarViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val estado by viewModel.estado.collectAsState()

    // Manejar navegación y mensajes según el estado
    LaunchedEffect(estado) {
        when (estado) {
            is RegistrarViewModel.RegistroState.Registrado -> {
                val ruta = screen.PrincipalScreen.createRoute(
                    java.net.URLEncoder.encode(nombre, "UTF-8"),
                    java.net.URLEncoder.encode(password, "UTF-8")
                )
                navController.navigate(ruta) {
                    popUpTo("registrar") { inclusive = true }
                }
            }
            is RegistrarViewModel.RegistroState.Error -> {
                val mensaje = (estado as RegistrarViewModel.RegistroState.Error).mensaje
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
            else -> {} // No hacer nada si está Idle o Cargando
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGeneral)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registrar Usuario",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = textoPrincipal,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.registrar(nombre.trim(), password.trim())
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                colors = buttonColors(containerColor = fondoBoton)
            ) {
                if (estado is RegistrarViewModel.RegistroState.Cargando) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                } else {
                    Text("Registrar", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(text = "Volver", color = Color.White)
            }
        }
    }
}