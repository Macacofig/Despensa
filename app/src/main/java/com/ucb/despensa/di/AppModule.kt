package com.ucb.despensa.di

import android.content.Context
import com.ucb.data.IProductosLocalDataSource
import com.ucb.data.IUsuariosLocalDataSource
import com.ucb.data.ProductosRepository
import com.ucb.data.UsuariosRepository
import com.ucb.framework.productos.ProductosGuardadosLocalDataSource
import com.ucb.framework.usuarios.UsuariosGuardadosLocalDataSource
import com.ucb.usecases.ActualizarProducto
import com.ucb.usecases.BuscarProductoCodigo
import com.ucb.usecases.EliminarProducto
import com.ucb.usecases.GuardarProducto
import com.ucb.usecases.GuardarUsuario
import com.ucb.usecases.ObtenerProductos
import com.ucb.usecases.ObtenerUsuario
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideProductosLocalDataSource(@ApplicationContext context: Context): IProductosLocalDataSource {
        return ProductosGuardadosLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideProductosRepository(localDataSource: IProductosLocalDataSource): ProductosRepository {
        return ProductosRepository(localDataSource)
    }

    @Provides
    @Singleton
    fun provideUsuariosLocalDataSource(@ApplicationContext context: Context): IUsuariosLocalDataSource {
        return UsuariosGuardadosLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideUsuariosRepository(localDataSource: IUsuariosLocalDataSource): UsuariosRepository {
        return UsuariosRepository(localDataSource)
    }

    //USECASES
    @Provides
    @Singleton
    fun provideGetProductos(repository: ProductosRepository): ObtenerProductos {
        return ObtenerProductos(repository)
    }

    @Provides
    @Singleton
    fun provideSaveProducto(repository: ProductosRepository): GuardarProducto {
        return GuardarProducto(repository)
    }

    @Provides
    @Singleton
    fun provideFindProducto(repository: ProductosRepository): BuscarProductoCodigo {
        return BuscarProductoCodigo(repository)
    }

    @Provides
    @Singleton
    fun provideActualizarProducto(repository: ProductosRepository): ActualizarProducto {
        return ActualizarProducto(repository)
    }
    @Provides
    @Singleton
    fun provideEliminarProducto(repository: ProductosRepository): EliminarProducto {
        return EliminarProducto(repository)
    }
    @Provides
    @Singleton
    fun provideGuardarUsuario(repository: UsuariosRepository): GuardarUsuario {
        return GuardarUsuario(repository)
    }

    @Provides
    @Singleton
    fun provideObtenerUsuario(repository: UsuariosRepository): ObtenerUsuario {
        return ObtenerUsuario(repository)
    }
}