package com.buvgm.data.model.local.entity

import com.buvgm.data.model.remote.dto.PlaceDto

data class Place(
    val Contacto: String,
    val Descripcion: String,
    val Nombre: String,
    val Precio: String,
)

fun Place.mapToDto() : PlaceDto = PlaceDto(
    Contacto = Contacto,
    Descripcion = Descripcion,
    Nombre = Nombre,
    Precio = Precio,
)
