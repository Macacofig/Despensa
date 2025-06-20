package com.ucb.framework.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductoGuardado::class,UsuarioGuardado::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun prodDao(): IProductoGuardadoDao
    abstract fun userDao(): IUsuarioGuardadoDao
    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            // Retorna la instancia si ya existe
            return INSTANCE ?: synchronized(this) {
                // Crea la instancia si es nula
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "despensa_database"
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}