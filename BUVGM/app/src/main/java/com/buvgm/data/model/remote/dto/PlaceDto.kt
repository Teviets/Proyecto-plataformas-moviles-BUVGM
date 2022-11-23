package com.buvgm.data.model.remote.dto

import com.buvgm.data.model.local.entity.Place

data class PlaceDto(
    val id: Int = 0,
    val contacto: String = "",
    val descripcion: String = "",
    val nombre: String = "",
    val precio: String = "",
    val favorite: Boolean = false
)

fun PlaceDto.mapToEntity() : Place = Place(
    id  = id,
    contacto = contacto,
    descripcion = descripcion,
    nombre = nombre,
    precio = precio,
    favorite = favorite
)
