package ru.mirea.kornilov.sway.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TripEntity::class], version = 2, exportSchema = false)
abstract class SwayDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS trips_new (
                        userId TEXT NOT NULL,
                        placeId INTEGER NOT NULL,
                        wantToVisit INTEGER NOT NULL,
                        visited INTEGER NOT NULL,
                        note TEXT NOT NULL,
                        PRIMARY KEY(userId, placeId)
                    )
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    INSERT INTO trips_new (userId, placeId, wantToVisit, visited, note)
                    SELECT '', placeId, wantToVisit, visited, note FROM trips
                    """.trimIndent()
                )
                db.execSQL("DROP TABLE trips")
                db.execSQL("ALTER TABLE trips_new RENAME TO trips")
            }
        }

        fun create(context: Context): SwayDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SwayDatabase::class.java,
                "sway.db"
            ).addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build()
        }
    }
}
