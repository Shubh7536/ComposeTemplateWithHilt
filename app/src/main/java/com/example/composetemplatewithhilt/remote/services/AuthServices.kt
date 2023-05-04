package com.example.composetemplatewithhilt.remote.services

import com.example.composetemplatewithhilt.data.Data
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthServices {
   @FormUrlEncoded
   @POST("auth/new/validate/email")
   suspend fun login( @Field("email") email: String):Response<Data>
}