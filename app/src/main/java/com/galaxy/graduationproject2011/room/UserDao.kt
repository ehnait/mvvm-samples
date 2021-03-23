package com.galaxy.graduationproject2011.room

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<User>


    @Query("select * from user where userName IN (:userName)")
    suspend fun findByName(userName: String): User

    @Query("delete from User")
    suspend fun deleteAllUser()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)


}
    