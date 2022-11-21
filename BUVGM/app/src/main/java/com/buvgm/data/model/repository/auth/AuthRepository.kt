package com.buvgm.data.model.repository.auth

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String) : String?
}
