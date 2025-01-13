package ru.korostylev.binsearcher.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext
        context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DB_NAME
    )
        .build()

    @Singleton
    @Provides
    fun provideCardInfoDao(
        appDatabase: AppDatabase
    ): CardInfoDao = appDatabase.cardInfoDao()

    companion object {

        private const val DB_NAME = "card_info.db"
    }
}
