package android.kode.data.localDB

import android.content.ContentValues
import android.kode.domain.models.UserModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userModel: UserModel)

    @Query("SELECT * FROM users_data_table")
    fun getLocalUsers(): LiveData<List<UserModel>>

}
