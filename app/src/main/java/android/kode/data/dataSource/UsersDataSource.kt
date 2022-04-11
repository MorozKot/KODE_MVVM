package android.kode.data.dataSource

import android.content.ContentValues
import android.kode.domain.models.UserModel
import android.util.Log
import androidx.lifecycle.LiveData

/**
 *@author Moroz V.A. on 06.03.2022
 **/

interface UsersDataSource {

    fun insert(userModel: UserModel)

    fun getLocalUsers(): LiveData<List<UserModel>>

    fun log() {
        Log.d(ContentValues.TAG, "UsersDataSource log")}

}