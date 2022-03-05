package android.kode.data.localDB

import android.kode.data.models.UsersModel
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *@author Moroz V.A. on 06.03.2022
 **/

@Database(entities = [UsersModel::class], version = 1)
abstract class LocalDB: RoomDatabase() {
    abstract val usersDao: UsersDao
}