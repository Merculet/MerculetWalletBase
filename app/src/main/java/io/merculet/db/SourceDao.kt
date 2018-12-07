package io.merculet.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import io.merculet.NewsConstants

/**
 * Created by Aaron on 04/11/17.
 */
@Dao
interface SourceDao {

    @Query("SELECT * FROM " + NewsConstants.T_SOURCE)
    fun getAllNewsSource(): LiveData<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(source: List<SourceEntity>)

    @Delete
    fun deleteSource(source: List<SourceEntity>)

//    fun insertSources(source: List<Source>) {
//
//        insertSources(*sourceEntityArray.toTypedArray())
//    }
}