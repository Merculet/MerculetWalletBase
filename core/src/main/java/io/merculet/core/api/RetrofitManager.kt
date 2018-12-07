package io.merculet.core.api

import io.merculet.core.BuildConfig
import io.merculet.core.utils.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager private constructor() {

    private val apiService: io.merculet.core.api.ApiService

    private val okHttpClient: OkHttpClient

    init {
        val builder = OkHttpClient.Builder()
        builder.writeTimeout((10 * 1000).toLong(), TimeUnit.MILLISECONDS)
        builder.readTimeout((10 * 1000).toLong(), TimeUnit.MILLISECONDS)
        builder.connectTimeout((10 * 1000).toLong(), TimeUnit.MILLISECONDS)

        val loggingInterceptor = io.merculet.core.api.interceptor.LoggingInterceptor.Builder()
                .loggable(true).request()
                .requestTag("Request")
                .response()
                .responseTag("Response")
        if (BuildConfig.DEBUG)
            loggingInterceptor.loggable(true) // TODO: 发布到生产环境需要改成false

        //设置拦截器
        builder.addInterceptor(io.merculet.core.api.interceptor.HeaderInterceptor())
        builder.addInterceptor(loggingInterceptor.build())

        okHttpClient = builder.build()

        mRetrofit = Retrofit.Builder()
                .baseUrl(io.merculet.core.api.ApiService.NEWSAPI_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build()

        apiService = mRetrofit.create(io.merculet.core.api.ApiService::class.java)
    }

    fun retrofit(): Retrofit = mRetrofit

    fun apiService(): io.merculet.core.api.ApiService = apiService

    fun okHttpClient(): OkHttpClient = okHttpClient

    private object Holder {
        val MANAGER = RetrofitManager()
    }

    companion object {

        private lateinit var mRetrofit: Retrofit

        @JvmStatic
        fun get(): RetrofitManager {

            return Holder.MANAGER
        }
    }
}