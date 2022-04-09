package android.kode.data.repositiry

import android.content.ContentValues
import android.kode.data.dataSource.UsersApiDataSource
import android.kode.data.dataSource.UsersDataSource
import android.kode.domain.models.UserModel
import android.kode.presentation.GetUsersResult
import android.kode.domain.repository.UsersCall
import android.util.Log
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersRepository (private val usersApiDataSource: UsersApiDataSource,
                       private val usersDataSource: UsersDataSource
): UsersCall {

    override suspend fun getUsers(): GetUsersResult {

        Log.d(ContentValues.TAG, "UsersRepository getUsers")

        return usersApiDataSource.getUsers()

    }

    override fun getLocalUsers(): LiveData<List<UserModel>> {

        Log.d(ContentValues.TAG, "UsersRepository getLocalUsers")

        return usersDataSource.getLocalUsers()
    }
}

