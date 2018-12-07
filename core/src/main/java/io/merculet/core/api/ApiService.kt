package io.merculet.core.api

import android.arch.lifecycle.LiveData
import io.merculet.ui.model.ArticlesResponse
import io.merculet.ui.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Tony Shen on 2017/8/15.
 */
interface ApiService {


    //采用第三方api -- https://newsapi.org
    companion object {
        val NEWSAPI_URL = "http://newsapi.org/v2/"

    }

    @GET("sources")
    fun getNews(@Query("language") language: String?,
                @Query("category") category: String?,
                @Query("country") country: String?,
                @Query("apiKey") apiKey: String): LiveData<ApiResponse<SourceResponse>>


    /*
    //多参数上传+多图片上传
    @Multipart
    @POST("userFeedback/uploadFeedback")
    fun uploadFile(@Part("email") email: String?,
                   @Part("subject") subject: String?,
                   @Part("content") content: String?,
                   @Part part: List<MultipartBody.Part>?): Maybe<HttpResponse<Any>>

    //用户反馈,未登录
    @Multipart
    @POST("userFeedback/uploadFeedbackWithoutLogin")
    fun uploadFileVisitor(@Part("email") email: String?,
                   @Part("subject") subject: String?,
                   @Part("content") content: String?,
                   @Part part: List<MultipartBody.Part>?): Maybe<HttpResponse<Any>>*/

}
