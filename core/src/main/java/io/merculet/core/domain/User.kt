package io.merculet.core.domain

import java.io.Serializable

/**
 * @author Aaron
 * @email aaron@merculet.io
 * @date 16/11/2017 2:34 PM
 * @description
 */
class User private constructor() : Serializable {

    var access_token: String = ""
    //todo: mock
//    var access_token: String = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTIzMjQ0ODgzLCJleHAiOjE1MjM0MTc2ODMsInBob25lX25vIjoiMTIzNDU2Nzg5MDEiLCJvcyI6IjAiLCJvc192ZXJzaW9uIjoiNi4wLjEiLCJhcHBfdmVyc2lvbiI6IjEuMCIsImNoYW5uZWwiOiIifQ.RKT-W3eYH2yvnuIeBQezs-nShYJRrAkF9RD0zCSbFdE"
    var userName: String = "User"
    var telephone: String = ""
    var country: String = ""
    var zondCode: String = ""

    init {
        if (io.merculet.core.domain.Settings.getUserName().isNotBlank())
            this.userName = io.merculet.core.domain.Settings.getUserName()
        if (io.merculet.core.domain.Settings.getToken().isNotBlank())
            this.access_token = io.merculet.core.domain.Settings.getToken()
        if (io.merculet.core.domain.Settings.getTelephone().isNotBlank())
            this.telephone = io.merculet.core.domain.Settings.getTelephone()
        if (io.merculet.core.domain.Settings.getCountry().isNotBlank())
            this.country = io.merculet.core.domain.Settings.getCountry()
        if (io.merculet.core.domain.Settings.getCountry().isNotBlank())
            this.zondCode = io.merculet.core.domain.Settings.getZoneCode()
    }

    companion object {
        var currentUser: User = User()
    }

    fun isLogin(): Boolean = access_token.isNotBlank()

    fun login(userLogin: io.merculet.core.domain.response.LoginResponse.LoginItem) {
        io.merculet.core.domain.Settings.setToken(userLogin.token)
        io.merculet.core.domain.Settings.setUserName("User")

        //init
        this.access_token = userLogin.token
        this.userName = "User"
        currentUser = this@User
    }

    fun logout() {
        io.merculet.core.domain.Settings.setToken("")
        io.merculet.core.domain.Settings.setUserName("")
        currentUser = User()

    }

}