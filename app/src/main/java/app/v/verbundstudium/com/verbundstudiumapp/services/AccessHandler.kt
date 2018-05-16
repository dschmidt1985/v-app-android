package app.v.verbundstudium.com.verbundstudiumapp.services

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

interface AccessHandler {

    fun getToken(): AccessToken?

    fun setToken(accessToken: AccessToken)

    fun getBaseUrl(): String

    fun isLoggedIn(): Boolean

    fun removeToken()
}

class BaseAccessHandler @Inject constructor(private val tokenStore: TokenStore): AccessHandler {

    private var token: AccessToken? = null


    override fun getBaseUrl(): String {
        return "https://v-app-200021.appspot.com/"
    }

    override fun removeToken() {
        this.token = null
        tokenStore.clearTokenData()
    }

    override fun getToken(): AccessToken? {
        if (token == null) {
            token = tokenStore.loadToken()
        }
        return token
    }

    override fun setToken(accessToken: AccessToken) {
        this.token = accessToken
        tokenStore.saveToken(accessToken)
    }

    override fun isLoggedIn(): Boolean {
        val token = getToken()
        return token != null && (token.isStillValid())
    }

}


data class AccessToken(@SerializedName("access_token") val token: String,
                       @SerializedName("expires_in") val expiresIn: Int,
                       val createdAt: Long) {

    fun isStillValid(): Boolean {
        val timeBetween = System.currentTimeMillis() - createdAt
        val timeBetweenInMinutes = timeBetween / 1000 / 60
        return timeBetweenInMinutes <= expiresIn + 10
    }

}
