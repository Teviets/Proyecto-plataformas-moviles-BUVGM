package com.buvgm.data.model.remote.api

import com.buvgm.data.model.Resource

interface AuthApi {
    suspend fun signInWithEmailAndPassword(email: String, password: String) : Resource<String>
}