package app.v.verbundstudium.com.verbundstudiumapp.services

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {


    data class LoginBody(@SerializedName("username") val username:String, @SerializedName("password") val password:String)

    @Headers("Content-type: application/json")
    @POST("/v1/ilias/token")
    fun loginUser(@Body loginData:LoginBody):Observable<AccessToken>
}