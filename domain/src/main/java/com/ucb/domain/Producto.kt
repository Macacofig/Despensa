package com.ucb.domain

data class Producto (
    val nombreProducto : String,
    val codigoProducto : String,
    val cantidad : Int,
    val usuario_id : Int? = null
)