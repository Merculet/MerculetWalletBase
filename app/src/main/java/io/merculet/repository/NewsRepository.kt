package io.merculet.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import io.merculet.BuildConfig
import io.merculet.RateLimiter
import io.merculet.core.api.ApiResponse
import io.merculet.core.api.ApiService
import io.merculet.core.api.RetrofitManager
import io.merculet.core.repository.NetworkBoundResource
import io.merculet.core.repository.NetworkOnlyResource
import io.merculet.core.vo.Resource
import io.merculet.db.NewsDBHelper
import io.merculet.db.SourceEntity
import io.merculet.ui.model.ArticlesResponse
import io.merculet.ui.model.SourceResponse
import java.util.concurrent.TimeUnit

/**
 * @Author Aaron
 * @Email aaron@merculet.io
 * @Description
 */
class NewsRepository(private val apiInterface: ApiService = RetrofitManager.get().apiService()) {

    val repoRateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun fetchNews(context: Context, language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return object : NetworkBoundResource<List<SourceEntity>, SourceResponse>() {
            override fun onFetchFailed() {
                repoRateLimiter.reset("all")
            }

            override fun saveCallResult(item: SourceResponse) {
//                To avoid this make API response pojo class as entity
                val sourceList = ArrayList<SourceEntity>()
                item.sources.forEach {
                    val sourceEntity = SourceEntity()
                    sourceEntity.id = it.id
                    sourceEntity.category = it.category
                    sourceEntity.country = it.country
                    sourceEntity.description = it.description
                    sourceEntity.language = it.language
                    sourceEntity.name = it.name
                    sourceEntity.url = it.url
                    sourceList.add(sourceEntity)
                }
                NewsDBHelper.getInstance(context).getSourceDao().insertSources(sourceList)
            }

            override fun shouldFetch(data: List<SourceEntity>?): Boolean = repoRateLimiter.shouldFetch("all")

            override fun loadFromDb(): LiveData<List<SourceEntity>> {
                return NewsDBHelper.getInstance(context).getSourceDao().getAllNewsSource()
            }

            override fun createCall(): LiveData<ApiResponse<SourceResponse>> {
                return apiInterface.getNews(language, category, country, BuildConfig.API_KEY)
            }
        }.asLiveData()
    }
}