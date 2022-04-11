package android.kode.data.dataSourceImpl

import android.content.ContentValues
import android.kode.data.dataSource.UsersDataSource
import android.kode.data.localDB.UsersDao
import android.kode.domain.models.UserModel
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *@author Moroz V.A. on 06.03.2022
 **/

class UsersDataSourceImpl(private val dao: UsersDao) :
    UsersDataSource {

    override fun insert(userModel: UserModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(userModel)
        }
    }

    override fun getLocalUsers(): LiveData<List<UserModel>> {
        return dao.getLocalUsers()
    }

}
