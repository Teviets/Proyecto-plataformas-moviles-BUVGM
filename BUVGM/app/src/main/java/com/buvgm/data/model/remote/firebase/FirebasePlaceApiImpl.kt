package com.buvgm.data.model.remote.firebase

import android.util.Log
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

    override suspend fun getByFavorite(isFavorite: Boolean): List<PlaceDto>? {
        return try {
            val res = db
                .collection("Productos")
                .whereEqualTo("favorite", isFavorite)
                .get()
                .await()

            res?.documents?.map { documentSnapshot ->
                documentSnapshot.toObject<PlaceDto>()!!
            }
        } catch (e: Exception) {
            Log.e("Firebase error", e.toString())
            return null
        }

    }

    override suspend fun getBySearch(nombre: String): List<PlaceDto>? {
        return try {
            val res = db
                .collection("Productos")
                .whereEqualTo("nombre",nombre)
                .get()
                .await()

            res?.documents?.map { documentSnapshot ->
                documentSnapshot.toObject<PlaceDto>()!!
            }
        } catch (e: Exception) {
            Log.e("Firebase error", e.toString())
            return null
        }
    }


}