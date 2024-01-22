package com.project.retrofittodoexampractice.core.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class Logger: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.nanoTime()
        Log.d("debugging","Sending request to ${request.url()}")

        val response = chain.proceed(request)
        val endTime = System.nanoTime()
        val duration = endTime - startTime
        Log.d("debugging","Received response in ${duration / 1e6} milliseconds")

        return  response
    }
}