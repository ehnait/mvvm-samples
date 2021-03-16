package com.galaxy.graduationproject2011.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "basic-sample-db"
        private lateinit var sInstance: AppDatabase
        fun getInstance(context: Context): AppDatabase {
            if (::sInstance.isInitialized.not()) {
                synchronized(AppDatabase::class.java) {
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return sInstance
        }
    }


}
    