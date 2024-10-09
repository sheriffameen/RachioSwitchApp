package com.example.rachioapp.base.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        //Need to retrieve from API and store in sharedPref
        requestBuilder.addHeader("Authorization", "Bearer 599c4261-103d-4e9a-b5c4-06558c7fcbe9")

        return chain.proceed(requestBuilder.build())
    }

}