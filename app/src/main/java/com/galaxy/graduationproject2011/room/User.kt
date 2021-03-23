package com.galaxy.graduationproject2011.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity 标识这个类用于建表，表名(默认不写，则用类名小写作为表名)
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "userName") val userName: String?,
    @ColumnInfo(name = "userPassword") val userPassword: String?

)