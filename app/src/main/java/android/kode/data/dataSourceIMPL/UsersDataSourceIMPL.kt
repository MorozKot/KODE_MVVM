package android.kode.data.dataSourceIMPL

import android.content.ContentValues
import android.kode.data.dataSource.UsersDataSource
import android.kode.data.localDB.UsersDao
import android.kode.data.models.UsersModel
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *@author Moroz V.A. on 06.03.2022
 **/

class UsersDataSourceIMPL(private val dao: UsersDao):
    UsersDataSource {

    override fun insert(usersModel: UsersModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(usersModel)
        }
    }

    override fun log() {
        Log.d(ContentValues.TAG, "UsersDataSourceIMPL")}

    override fun loadUsers(): LiveData<List<UsersModel>> {
        return dao.loadUsers()
    }

    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()
        }
    }
}