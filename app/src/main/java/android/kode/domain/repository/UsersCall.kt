package android.kode.domain.repository

import android.content.ContentValues
import android.content.Context
import android.kode.data.models.UsersModel
import android.util.Log
import androidx.lifecycle.LiveData

interface UsersCall {

    suspend fun getUsers(context: Context): GetUsersResult

    fun loadUsers(): LiveData<List<UsersModel>>

    fun log() {
        Log.d(ContentValues.TAG, "UsersCall")
    }

}
