package android.kode.data.dataSource

import android.content.ContentValues
import android.kode.data.models.UsersModel
import android.util.Log
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 06.03.2022
 **/

interface UsersDataSource {

    fun insert(usersModel: UsersModel)

    fun log() {
        Log.d(ContentValues.TAG, "UsersDataSource")}

    fun loadUsers(): LiveData<List<UsersModel>>

    suspend fun clear()
}