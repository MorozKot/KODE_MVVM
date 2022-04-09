package android.kode.domain.repository

import android.kode.domain.models.UserModel
import android.kode.presentation.GetUsersResult
import androidx.lifecycle.LiveData

interface UsersCall {

    suspend fun getUsers(): GetUsersResult

    fun getLocalUsers(): LiveData<List<UserModel>>

}
