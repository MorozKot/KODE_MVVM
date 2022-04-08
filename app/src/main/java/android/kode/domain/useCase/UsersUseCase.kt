package android.kode.domain.useCase

import android.content.Context
import android.kode.data.models.UsersModel
import android.kode.domain.repository.UsersCall
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersUseCase (private val usersCall: UsersCall) {

    fun loadUsers(): LiveData<List<UsersModel>> {

        return usersCall.loadUsers()

    }

    suspend fun startMigration (context: Context) {

        usersCall.startMigration(context)

    }

}