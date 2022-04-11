package android.kode.domain.useCase

import android.content.ContentValues.TAG
import android.kode.domain.models.UserModel
import android.kode.presentation.GetUsersResult
import android.kode.domain.repository.UsersCall
import android.util.Log
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersUseCase (private val usersCall: UsersCall) {

    suspend fun startGetUsers(): GetUsersResult {

        Log.d(TAG, "UsersUseCase startGetUsers")

        return  usersCall.getUsers()}

    fun getLocalUsers(): LiveData<List<UserModel>> {

        Log.d(TAG, "UsersUseCase getLocalUsers")

        return usersCall.getLocalUsers()
    }
}