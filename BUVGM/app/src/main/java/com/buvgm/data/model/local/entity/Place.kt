package com.buvgm.data.model.local.entity

import com.buvgm.data.model.remote.dto.PlaceDto

data class Place(
    val id: Int,
    val contacto: String,
    val descripcion: String,
    val nombre: String,
    val precio: String,
    val favorite: Boolean
)

fun Place.mapToDto() : PlaceDto = PlaceDto(
    id = id,
    contacto = contacto,
    descripcion = descripcion,
    nombre = nombre,
    precio = precio,
    favorite = favorite
)
