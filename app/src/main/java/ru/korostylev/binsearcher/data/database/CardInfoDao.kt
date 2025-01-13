package ru.korostylev.binsearcher.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardInfoDao {

    @Query("SELECT * FROM CardInfoEntity ORDER BY id DESC")
    fun getCardInfoList(): LiveData<List<CardInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCardInfo(cardInfoEntity: CardInfoEntity)

    @Query("DELETE FROM CardInfoEntity WHERE id=:cardInfoId")
    fun deleteCardInfo(cardInfoId: Int)

    @Query("SELECT * FROM CardInfoEntity WHERE id=:shopItemId LIMIT 1")
    fun getCardInfo(shopItemId: Int): CardInfoEntity
}