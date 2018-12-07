package io.merculet.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.merculet.NewsConstants

/**
 * Created by Aaron on 04/11/17.
 */

@Entity(tableName = NewsConstants.T_SOURCE)
class SourceEntity(
        @PrimaryKey()
        var id: String = "",
        var name: String? = "",
        var description: String? = "",
        var url: String? = "",
        var category: String? = "",
        var language: String? = "",
        var country: String? = ""
)