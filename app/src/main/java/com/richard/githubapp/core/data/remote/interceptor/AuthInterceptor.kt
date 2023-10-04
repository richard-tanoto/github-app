package com.richard.githubapp.core.data.remote.interceptor

import com.richard.githubapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Authorization", "token ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(request)
    }
}