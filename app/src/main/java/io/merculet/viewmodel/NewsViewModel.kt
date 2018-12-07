package io.merculet.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import io.merculet.db.SourceEntity
import io.merculet.ui.model.ArticlesResponse
import io.merculet.repository.NewsRepository
import io.merculet.core.vo.Resource

/**
 * Created by Aaron on 01/11/17.
 */
class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepo: NewsRepository = NewsRepository()

    fun getNewsSource(language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return newsRepo.fetchNews(getApplication(), language, category, country)
    }

}