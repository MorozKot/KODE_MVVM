package android.kode.data.repositiry

import android.content.ContentValues
import android.content.Context
import android.kode.data.dataSource.UsersApiDataSource
import android.kode.data.dataSource.UsersDataSource
import android.kode.data.models.UsersModel
import android.kode.domain.repository.GetUsersResult
import android.kode.domain.repository.UsersCall
import android.util.Log
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersRepository (private val usersApiDataSource: UsersApiDataSource,
                       private val usersDataSource: UsersDataSource
): UsersCall {

    override suspend fun getUsers(): GetUsersResult = usersApiDataSource.getUsers()

    override fun loadUsers(): LiveData<List<UsersModel>> {
        return usersDataSource.loadUsers()
    }

    override fun log() {
        Log.d(ContentValues.TAG, "UsersRepository")}

}

