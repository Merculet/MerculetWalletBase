package io.merculet.viewmodel

import android.arch.lifecycle.MutableLiveData
import io.merculet.core.viewmodel.BaseViewModel

class MainViewModel :BaseViewModel(){

    private val result = MutableLiveData<Boolean>()


    fun getData() {

    }

}