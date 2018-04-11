package app.v.verbundstudium.com.verbundstudiumapp.services

import javax.inject.Inject

interface TokenStore {

    fun clearTokenData()
    fun loadToken(): AccessToken?
    fun saveToken(token: AccessToken)

}

class BaseTokenStore @Inject constructor(): TokenStore {
    override fun clearTokenData() {
        accessToken = null
    }

    override fun loadToken(): AccessToken? {
        return accessToken
    }

    override fun saveToken(token: AccessToken) {
        accessToken = token
    }

    //TODO use another cache
    companion object {
        private var accessToken: AccessToken? = null
    }



}