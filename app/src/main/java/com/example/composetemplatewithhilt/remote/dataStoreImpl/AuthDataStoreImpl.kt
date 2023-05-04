package com.example.composetemplatewithhilt.remote.dataStoreImpl

import com.example.composetemplatewithhilt.data.dataStores.AuthDataStore
import com.example.composetemplatewithhilt.remote.services.AuthServices
import javax.inject.Inject

class AuthDataStoreImpl @Inject constructor(private val authServices: AuthServices) :AuthDataStore{
    override suspend fun loginDataStore(email:String) {
        println("AuthDataStoreImpl")
        authServices.login(email)
    }
}