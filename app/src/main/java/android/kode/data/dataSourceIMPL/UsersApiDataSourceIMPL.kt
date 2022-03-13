package android.kode.data.dataSourceIMPL

import android.content.Context
import android.kode.data.api.ApiClient
import android.kode.data.dataSource.UsersApiDataSource
import android.kode.data.dataSource.UsersDataSource
import android.kode.data.models.UsersApiModel
import android.kode.data.models.UsersListApiModel
import android.kode.data.models.UsersModel
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *@author Moroz V.A. on 06.03.2022
 **/

class UsersApiDataSourceIMPL(private val usersDataSource: UsersDataSource) :
    UsersApiDataSource {

    //  private val usersListResponse = MutableLiveData<List<UsersApiModel>>()

    override fun startMigration(context: Context) {

        val call = ApiClient.instance?.api?.getUsers()
        call?.enqueue(object : Callback<UsersListApiModel> {
            override fun onResponse(
                call: Call<UsersListApiModel>,
                response: Response<UsersListApiModel>
            ) {

                // usersListResponse.value = response.body()!!.items

                var usersApiModel: ArrayList<UsersApiModel>? = null

                usersApiModel?.clear()

                usersApiModel = (response.body()!!.items as ArrayList<UsersApiModel>)!!

                for (audit in usersApiModel) { //запихиваем в локальную БД UsersModel

                    UsersModel(
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
                        usersDataSource.insert(
                            it
                        )
                    }

                    Toast.makeText(context, "Секундочку, гружусь...", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UsersListApiModel>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Не могу обновить данные. Что-то пошло не так",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }
}