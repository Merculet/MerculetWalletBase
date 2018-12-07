package io.merculet.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.merculet.NewsConstants

/**
 * Created by Aaron on 04/11/17.
 */

@Entity(tableName = NewsConstants.T_ARTICLE)
data class ArticleEntity(
        @PrimaryKey
        var title: String = "",
        var source: String? = "",
        var author: String? = "",
        var description: String? = "",
        var url: String? = "",
        var urlToImage: String? = "",
        var publishedAt: String? = ""
)