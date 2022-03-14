package android.kode.domain.repository

import android.content.Context
import android.kode.data.models.UsersModel
import androidx.lifecycle.LiveData

interface UsersCall {

    fun loadCoffee(): LiveData<List<UsersModel>>

    suspend fun startMigration(context: Context)

}
