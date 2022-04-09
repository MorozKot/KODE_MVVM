package android.kode.data.localDB

import android.content.ContentValues
import android.kode.data.models.UsersModel
import android.util.Log
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

    fun log() {
        Log.d(ContentValues.TAG, "UsersDao")}

    @Query("DELETE FROM users_data_table")
    suspend fun clear()
}
