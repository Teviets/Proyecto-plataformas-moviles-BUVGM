package com.buvgm.data.model.repository.place

import com.buvgm.data.model.local.entity.Place
import com.buvgm.data.model.local.entity.mapToDto
import com.buvgm.data.model.remote.api.PlaceApi
import com.buvgm.data.model.remote.dto.mapToEntity

class PlaceRepositoryImpl(
    private val api: PlaceApi,
) : PlaceRepository {
    override suspend fun createPlace(place: Place) {
        val dtoToInsert = place.mapToDto()

        api.insert(dtoToInsert)
    }

    override suspend fun getPlace(): List<Place>? {
        val AllProductos = api.AllProducts()
        return AllProductos?.map { dto -> dto.mapToEntity()}
    }

    override suspend fun getPlaceFavorite(isFavorite: Boolean): List<Place>? {
        val getByFavorite = api.getByFavorite(isFavorite)
        return getByFavorite?.map { dto -> dto.mapToEntity()}
    }

    override suspend fun getPlaceSearch(nombre: String): List<Place>? {
        val getBySearch = api.getBySearch(nombre)
        return getBySearch?.map { dto -> dto.mapToEntity()}
    }
}