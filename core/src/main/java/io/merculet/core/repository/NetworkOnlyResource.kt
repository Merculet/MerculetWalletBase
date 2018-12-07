package io.merculet.core.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import io.merculet.core.vo.Resource

/**
 * @Author Aaron
 * @Email aaron@merculet.io
 * @Description  如果不需要DB存储，则用此类。可以省略部分相关代码
 */
abstract class NetworkOnlyResource<RequestType> @MainThread constructor() : NetworkBoundResource<RequestType, RequestType>() {
    private val result = MediatorLiveData<Resource<RequestType>>()

    // 将网络获取的数据存储到db
    @WorkerThread
    override fun saveCallResult(item: RequestType) {
        liveData.postValue(item)
    }


    // 是否需要从网络重新获取
    @MainThread
    override fun shouldFetch(data: RequestType?): Boolean {
        return true
    }

    // 从db内获取cache数据
    @MainThread
    override fun loadFromDb(): LiveData<RequestType> {
        return liveData
    }

    // 从网络中获取
    @MainThread
    abstract override fun createCall(): LiveData<io.merculet.core.api.ApiResponse<RequestType>>

    // 获取失败
    @MainThread
    override fun onFetchFailed() {
    }

}