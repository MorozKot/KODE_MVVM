package android.kode.domain.useCase

import android.content.ContentValues.TAG
import android.content.Context
import android.kode.data.models.UsersModel
import android.kode.domain.repository.GetUsersResult
import android.kode.domain.repository.UsersCall
import android.util.Log
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersUseCase (private val usersCall: UsersCall) {

    suspend fun start(context: Context): GetUsersResult {

        Log.d(TAG, "UsersUseCase start")

        return  usersCall.getUsers(context)}

    fun loadUsers(): LiveData<List<UsersModel>> {

        Log.d(TAG, "UsersUseCase loadUsers")

        return usersCall.loadUsers()
    }
}