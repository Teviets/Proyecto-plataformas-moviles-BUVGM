package com.buvgm.data.model.repository.auth

import com.buvgm.data.model.Resource
import com.buvgm.data.model.remote.api.AuthApi

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun signInWithEmailAndPassword(email: String, password: String) : String? {
        val authResponse = api.signInWithEmailAndPassword(email, password)

        return if (authResponse is Resource.Success)
            authResponse.data!!
        else
            null
    }
}