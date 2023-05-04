package com.example.composetemplatewithhilt.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor() :Interceptor{
    private var headers: Map<String, String?> = emptyMap()
    private var queryParams: Map<String, String> = emptyMap()
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(buildRequest(chain.request(),""))
    }

    private fun buildRequest(originalRequest: Request, token:String?): Request {

        // Original Request Object
        val requestBuilder = originalRequest.newBuilder()

        //Adding Headers
        for (key in headers.keys) {
            if (headers[key] != null)
                requestBuilder.addHeader(key, headers[key]!!)
            else throw IllegalArgumentException("Illegal argument ${headers[key]} for ${key}")

        }

        if (!token.isNullOrEmpty())
            requestBuilder.addHeader("Authorization","Bearer $token")


        //Adding Query Params
        if (queryParams.isNotEmpty()) {
            val originalHttpUrl = originalRequest.url

            val urlBuilder = originalHttpUrl.newBuilder()
            for (key in queryParams.keys) {

                if (queryParams[key] != null) {
                    urlBuilder.addQueryParameter(key, queryParams[key])
                }
            }
            requestBuilder.url(urlBuilder.build())
        }

        return requestBuilder.build()
    }

}