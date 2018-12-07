package io.merculet.core.domain

class ServiceException(message: String) : Exception(message) {
    val error: io.merculet.core.domain.ErrorEnvelope

    init {

        error = io.merculet.core.domain.ErrorEnvelope(message)
    }
}
