package android.kode.data.repositiry

import android.content.Context
import android.kode.data.dataSource.UsersApiDataSource
import android.kode.data.dataSource.UsersDataSource
import android.kode.data.models.UsersModel
import android.kode.domain.repository.UsersCall
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersRepository (private val usersApiDataSource: UsersApiDataSource,
                       private val usersDataSource: UsersDataSource
): UsersCall {

    //val products = dao.loadCoffee()

    override fun loadCoffee(): LiveData<List<UsersModel>> {
        return usersDataSource.loadUsers()
    }


    override suspend fun startMigration(context: Context) {
        usersDataSource.clear()
        usersApiDataSource.startMigration(context)
    }
}

