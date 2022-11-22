package com.buvgm.data.model.repository.place

import com.buvgm.data.model.local.entity.Place

interface PlaceRepository {
    suspend fun createPlace(place: Place)
    suspend fun getPlace(): List<Place>?
}