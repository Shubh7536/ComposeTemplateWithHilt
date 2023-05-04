package com.example.composetemplatewithhilt.data.repository

import com.example.composetemplatewithhilt.data.dataStores.AuthDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authDataStore: AuthDataStore
) {
    suspend fun login(email:String){
        println("AuthRepository")
        authDataStore.loginDataStore(email)
    }
}