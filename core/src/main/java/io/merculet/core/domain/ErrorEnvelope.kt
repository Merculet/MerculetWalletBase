package io.merculet.core.domain

class ErrorEnvelope @JvmOverloads constructor(val code: Int, val message: String?, private val throwable: Throwable? = null) {

    constructor(message: String?) : this(io.merculet.core.domain.Config.ErrorCode.UNKNOWN, message)
}
