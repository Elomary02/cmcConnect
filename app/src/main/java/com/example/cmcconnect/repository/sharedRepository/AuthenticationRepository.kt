package com.example.cmcconnect.repository.sharedRepository

interface AuthenticationRepository {
    suspend fun signIn(email:String, password:String): Boolean
    suspend fun getCurrentUserEmail(): String
}