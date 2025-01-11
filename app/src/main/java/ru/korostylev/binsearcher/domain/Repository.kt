package ru.korostylev.binsearcher.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface Repository {

    suspend fun getCardInfo(bin: Int): LiveData<CardInfo>

}