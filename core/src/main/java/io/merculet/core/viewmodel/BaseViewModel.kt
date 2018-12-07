package io.merculet.core.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val error = MutableLiveData<io.merculet.core.domain.ErrorEnvelope>()
    protected val progress = MutableLiveData<Boolean>()
    protected var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        cancel()
    }

    protected fun cancel() {
        compositeDisposable.clear()
    }

    fun error(): LiveData<io.merculet.core.domain.ErrorEnvelope> {
        return error
    }

    fun progress(): LiveData<Boolean> {
        return progress
    }

    protected fun onError(throwable: Throwable) {
        if (throwable is io.merculet.core.domain.ServiceException) {
            error.postValue(throwable.error)
        } else {
            error.postValue(io.merculet.core.domain.ErrorEnvelope(io.merculet.core.domain.Config.ErrorCode.UNKNOWN, null, throwable))
            // TODO: Add dialog with offer send error log to developers: notify about error.
            Log.d("SESSION", "Err", throwable)
        }
    }
}
