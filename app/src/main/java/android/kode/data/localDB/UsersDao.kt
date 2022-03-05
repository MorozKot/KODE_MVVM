package android.kode.data.localDB

import android.kode.data.models.UsersModel
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(usersModel: UsersModel)

    @Query("SELECT * FROM users_data_table")
    fun loadUsers(): LiveData<List<UsersModel>>

    @Query("DELETE FROM users_data_table")
    suspend fun clear()
}
