package android.kode.data.api

import android.kode.data.models.UsersListApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 *@author Moroz V.A. on 06.03.2022
 **/

interface ApiInterface {
    @Headers(
        "Content-Type: application/json",
        "Prefer: code=500, example=error-500"
    )

    @GET("users")
    fun getUsers(): Call<UsersListApiModel>
}
