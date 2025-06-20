package com.ucb.framework.mappers

import com.ucb.domain.Producto
import com.ucb.domain.Usuario
import com.ucb.framework.persistence.ProductoGuardado
import com.ucb.framework.persistence.UsuarioGuardado

fun Producto.toEntity(): ProductoGuardado {
    return ProductoGuardado(
        nombreProducto = this.nombreProducto,
        codigoProducto = this.codigoProducto,
        cantidad = this.cantidad,
        usuario_id = this.usuario_id
    )
}

fun ProductoGuardado.toDomain(): Producto {
    return Producto(
        nombreProducto = this.nombreProducto,
        codigoProducto = this.codigoProducto,
        cantidad = this.cantidad,
        usuario_id = this.usuario_id
    )
}

fun Usuario.toEntityU(): UsuarioGuardado {
    return UsuarioGuardado(
        nombre = this.nombre,
        password = this.password
    )
}

// UsuarioGuardado → Usuario
fun UsuarioGuardado.toDomainU(): Usuario {
    return Usuario(
        nombre = this.nombre,
        password = this.password
    )
}