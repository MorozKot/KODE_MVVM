package android.kode.data.dataSource

import android.content.ContentValues
import android.content.Context
import android.kode.domain.repository.GetUsersResult
import android.util.Log

/**
 *@author Moroz V.A. on 06.03.2022
 **/
interface UsersApiDataSource {

    suspend fun getUsers() : GetUsersResult

    fun log() {
        Log.d(ContentValues.TAG, "UsersApiDataSource")
    }
}