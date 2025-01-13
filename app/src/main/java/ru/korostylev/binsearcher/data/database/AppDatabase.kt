package ru.korostylev.binsearcher.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardInfoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cardInfoDao(): CardInfoDao

}