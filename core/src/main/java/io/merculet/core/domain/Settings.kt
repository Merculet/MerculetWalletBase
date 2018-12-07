package io.merculet.core.domain

import android.annotation.SuppressLint
import io.merculet.core.utils.SPHelper

/**
 * @author Aaron
 * @email aaron@merculet.io
 * @date 30/03/2018 14:35
 * @description
 */
object Settings {
    @SuppressLint("StaticFieldLeak")
    val spHelper = SPHelper.create(io.merculet.core.BaseApplication.instance)

    val SP_TOKEN = "sp_token"
    val SP_USERNAME = "sp_username"
    val SP_TELEPHONE = "sp_telephone"
    val SP_COUNTRY = "sp_country"
    val SP_ZONECODE = "sp_zone_code"

    fun setZoneCode(zoneCode: String) = if (zoneCode.isNotBlank()) spHelper.put(SP_ZONECODE, zoneCode) else spHelper.remove(SP_ZONECODE)

    fun getZoneCode(): String = spHelper[SP_ZONECODE, ""]

    fun setCountry(country: String) = if (country.isNotBlank()) spHelper.put(SP_COUNTRY, country) else spHelper.remove(SP_COUNTRY)

    fun getCountry(): String = spHelper[SP_COUNTRY, ""]

    fun setTelephone(telephone: String) = if (telephone.isNotBlank()) spHelper.put(SP_TELEPHONE, telephone) else spHelper.remove(SP_TELEPHONE)

    fun getTelephone(): String = spHelper[SP_TELEPHONE, ""]

    fun setToken(token: String) = if (token.isNotBlank()) spHelper.put(SP_TOKEN, token) else spHelper.remove(SP_TOKEN)

    fun getToken(): String = spHelper[SP_TOKEN, ""]

    fun setUserName(userName: String) = if (userName.isBlank()) spHelper.put(SP_USERNAME, userName) else spHelper.remove(SP_USERNAME)

    fun getUserName(): String = spHelper[SP_USERNAME, ""]
}