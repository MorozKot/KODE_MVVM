package android.kode.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_data_table")
class UsersModel (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val id: String,

    @ColumnInfo(name = "user_name")
    val avatarUrl: String,

    @ColumnInfo(name = "user_image")
    val firstName: String,

    @ColumnInfo(name = "user_description")
    val lastName: String,

    @ColumnInfo(name = "user_price")
    val userTag: String,

    @ColumnInfo(name = "user_price")
    val department: String,

    @ColumnInfo(name = "user_price")
    val position: String,

    @ColumnInfo(name = "user_price")
    val birthday: String,

    @ColumnInfo(name = "user_price")
    val phone: String
)