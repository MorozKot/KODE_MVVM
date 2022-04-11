package android.kode.data.localDB

import android.kode.domain.models.UserModel
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *@author Moroz V.A. on 06.03.2022
 **/

@Database(entities = [UserModel::class], version = 1)
abstract class LocalDB: RoomDatabase() {
    abstract val usersDao: UsersDao
}