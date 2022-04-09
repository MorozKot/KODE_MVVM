package android.kode.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_data_table")
class UserModel (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val id: String,

    @ColumnInfo(name = "user_avatarUrl")
    val avatarUrl: String,

    @ColumnInfo(name = "user_firstName")
    val firstName: String,

    @ColumnInfo(name = "user_lastName")
    val lastName: String,

    @ColumnInfo(name = "user_userTag")
    val userTag: String,

    @ColumnInfo(name = "user_department")
    val department: String,

    @ColumnInfo(name = "user_position")
    val position: String,

    @ColumnInfo(name = "user_birthday")
    val birthday: String,

    @ColumnInfo(name = "user_phone")
    val phone: String
)