package app.v.verbundstudium.com.verbundstudiumapp.services

import okhttp3.Interceptor
import okhttp3.Response

class OkHttpHeaderInterceptor(private val accessHandler: AccessHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")

        accessHandler.getToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer " + it.token)
        }

        return chain.proceed(requestBuilder.build())
    }
}