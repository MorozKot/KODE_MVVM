package android.kode.data.dataSourceImpl

import android.content.ContentValues.TAG
import android.kode.data.api.ApiClient
import android.kode.data.dataSource.UsersApiDataSource
import android.kode.data.dataSource.UsersDataSource
import android.kode.data.models.UserApiModel
import android.kode.data.models.UsersListApiModel
import android.kode.domain.models.UserModel
import android.kode.presentation.GetUsersResult
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 *@author Moroz V.A. on 06.03.2022
 **/

class UsersApiDataSourceImpl(private val usersDataSource: UsersDataSource) :
    UsersApiDataSource {

    var result: GetUsersResult =
        GetUsersResult.EnqueueError("ApiClient.instance?.api?.getUsers not working")

    override suspend fun getUsers(): GetUsersResult {

        val call = ApiClient.instance?.api?.getUsers()

        call?.enqueue(object : Callback<UsersListApiModel> {
            override fun onResponse(
                call: Call<UsersListApiModel>,
                response: Response<UsersListApiModel>
            ) {
                when {
                    response.isSuccessful -> {
                        val userApiModel: ArrayList<UserApiModel>

                        userApiModel = response.body()?.items as ArrayList<UserApiModel>

                        for (audit in userApiModel) {
                            UserModel(
                                audit.id,
                                audit.avatarUrl,
                                audit.firstName,
                                audit.lastName,
                                audit.userTag,
                                audit.department,
                                audit.position,
                                audit.birthday,
                                audit.phone
                            ).let {
                                usersDataSource.insert(it)
                            }
                        }
                        result = GetUsersResult.Success(error = "No error")

                        Log.d(TAG, "UsersApiDataSourceIMPL")
                        Log.d(TAG, result.toString())
                    }
                    response.code() in 400..499 -> {
                        result = GetUsersResult.ServerError(
                            error = "Client Error: ${
                                response.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                            }"
                        )
                    }
                    response.code() in 500..599 -> {
                        result = GetUsersResult.ServerError(
                            error = "Server Error: ${
                                response.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                            }"
                        )

                    }
                    else -> {
                        result = GetUsersResult.ServerError(
                            error = "Unknown Error: ${
                                response.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                            }"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<UsersListApiModel>, t: Throwable) {

                result = GetUsersResult.ConnectionError()
            }
        })
        return result
    }
}