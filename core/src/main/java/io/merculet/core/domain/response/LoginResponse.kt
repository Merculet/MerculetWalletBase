package io.merculet.core.domain.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginResponse : io.merculet.core.domain.response.HttpResponse<LoginResponse.LoginItem>() {
    data class LoginItem(@SerializedName("expires_in_milliseconds")
                    var expiresInMilliseconds: Long = 0L,
                    var token: String
    ) : Serializable
}
