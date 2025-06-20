package com.ucb.despensa.service

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class util {
    companion object {

        fun sendNotificatión(context: Context, mensaje: String) {
            // Verifica si ya se tiene el permiso
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                showNotification(context, mensaje)
            } else {
                requestPermission(context)
            }
        }

        @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
        private fun showNotification(context: Context, mensaje: String) {
            // Crear canal de notificación en Android 8.0+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    "default_channel",
                    "Canal por defecto",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Este es el canal de notificaciones por defecto."
                }

                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }

            // Crear la notificación
            val notification = NotificationCompat.Builder(context, "default_channel")
                .setContentTitle("Estado de televisores")
                .setContentText(mensaje)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Cambia el ícono si lo deseas
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            // Mostrar la notificación
            NotificationManagerCompat.from(context).notify(1, notification)
        }

        private fun requestPermission(context: Context) {
            // Solo si el contexto es una Activity, puede pedir permisos
            if (context is Activity) {
                ActivityCompat.requestPermissions(
                    context,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }
    }
}