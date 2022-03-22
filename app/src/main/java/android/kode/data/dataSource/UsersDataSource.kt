package android.kode.data.dataSource

import android.kode.data.models.UsersModel
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 06.03.2022
 **/

interface UsersDataSource {

    fun insert(usersModel: UsersModel)

    fun loadUsers(): LiveData<List<UsersModel>>

    suspend fun clear()
}