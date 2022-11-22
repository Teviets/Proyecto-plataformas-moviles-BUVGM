package com.buvgm.data.model.repository.place

interface PlaceRepository {
    suspend fun createPlace()
    suspend fun getPlace()
}