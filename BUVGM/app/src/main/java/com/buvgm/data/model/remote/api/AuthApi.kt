package com.buvgm.data.model.remote.api

interface AuthApi {
    suspend fun signInWithEmailAndPassword(email: String, password: String) : String?
}