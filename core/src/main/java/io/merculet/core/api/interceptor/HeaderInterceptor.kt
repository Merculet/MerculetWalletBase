package io.merculet.core.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author Aaron
 * @email aaron@merculet.io
 * @date 2017/7/13 11:44 AM
 * @description
 */

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val requestBuilder = request.newBuilder()

        requestBuilder.addHeader("os_type", "0")
        requestBuilder.addHeader("mw-token", io.merculet.core.domain.User.currentUser.access_token)

        return chain.proceed(requestBuilder.build())
    }
}