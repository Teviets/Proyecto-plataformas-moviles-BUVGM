package com.buvgm.data.model.remote.api

import com.buvgm.data.model.remote.dto.PlaceDto

interface PlaceApi {
    suspend fun insert(placeDto: PlaceDto)
    //suspend fun getByName(nombre: String): PlaceDto?
    suspend fun AllProducts(): List<PlaceDto>?
    suspend fun getByFavorite(isFavorite: Boolean): List<PlaceDto>?
    suspend fun getBySearch(nombre: String): List<PlaceDto>?
}