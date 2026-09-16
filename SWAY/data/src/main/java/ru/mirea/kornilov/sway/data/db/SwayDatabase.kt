package ru.mirea.kornilov.sway.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TripEntity::class], version = 1, exportSchema = false)
abstract class SwayDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao

    companion object {
        fun create(context: Context): SwayDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SwayDatabase::class.java,
                "sway.db"
            ).allowMainThreadQueries().build()
        }
    }
}
