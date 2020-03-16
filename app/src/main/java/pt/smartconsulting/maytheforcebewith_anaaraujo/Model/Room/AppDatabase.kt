package pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DataPeople::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataPeopleDao(): DataPeopleDao
}