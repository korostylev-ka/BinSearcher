package ru.korostylev.binsearcher.domain

import androidx.lifecycle.LiveData

interface Repository {

    suspend fun getCardInfo(bin: Int): LiveData<CardInfo>

    fun getListCardInfo(): LiveData<List<CardInfo>>

}