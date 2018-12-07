/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.merculet.core.vo

import io.merculet.core.vo.Status.ERROR
import io.merculet.core.vo.Status.LOADING
import io.merculet.core.vo.Status.SUCCESS

/**
 * A generic class that holds a value with its loading status.
 *
 * @param <T>
</T> */
class Resource<T>(var status: Status, var data: T?, var message: String?) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(data: T?, message: String): Resource<T> = Resource(Status.ERROR, data, message)
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }

    override fun toString(): String {
        return "Resource(status=$status, data=$data, message=$message)"
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 27 * result + (data?.hashCode() ?: 0)
        result = 27 * result + (message?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resource<*>

        if (status != other.status) return false
        if (data != other.data) return false
        if (message != other.message) return false

        return true
    }


    var cancelListener: (() -> Unit?)? = null
    fun setOnCancelListener(cancelListener: () -> Unit) {
        this.cancelListener = cancelListener
    }
}
