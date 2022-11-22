package com.buvgm.data.model.remote.firebase

import com.buvgm.data.model.remote.api.AuthApi
import com.buvgm.data.model.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthApiImpl(
    private val api: FirebaseAuth
) : AuthApi {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<String> {
        return try {
            val response = api.signInWithEmailAndPassword(email, password).await()

            val user = response.user
            if (user != null)
                Resource.Success(data = user.uid)
            else
                Resource.Error(message = "User not found")
        } catch (e: Exception) {
            Resource.Error(message = "User not found")
        }
    }
}