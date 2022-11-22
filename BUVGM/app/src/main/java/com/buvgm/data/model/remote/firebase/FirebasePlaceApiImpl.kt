package com.buvgm.data.model.remote.firebase

import com.buvgm.data.model.remote.api.PlaceApi
import com.buvgm.data.model.remote.dto.PlaceDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebasePlaceApiImpl(
    private val db: FirebaseFirestore
) : PlaceApi {
    override suspend fun insert(placeDto: PlaceDto) {
        db.collection("Productos")
            .add(placeDto)
            .await()
    }

    override suspend fun AllProducts(): List<PlaceDto>? {
        return try {
            val res = db
                .collection("Productos")
                .get()
                .await()

            res.documents.map { documentSnapshot ->
                documentSnapshot.toObject<PlaceDto>()!!
            }
        }catch (e: Exception){
            null
        }
    }


}