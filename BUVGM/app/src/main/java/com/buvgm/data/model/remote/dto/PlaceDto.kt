package com.buvgm.data.model.remote.dto

import com.buvgm.data.model.local.entity.Place

data class PlaceDto(
    val Contacto: String = "",
    val Descripcion: String = "",
    val Nombre: String = "",
    val Precio: String = "",
)

fun PlaceDto.mapToEntity() : Place = Place(
    Contacto = Contacto,
    Descripcion = Descripcion,
    Nombre = Nombre,
    Precio = Precio
)
