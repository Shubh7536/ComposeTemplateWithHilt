package com.example.composetemplatewithhilt.data.dataStores

interface AuthDataStore {
    suspend fun loginDataStore(email:String)
}