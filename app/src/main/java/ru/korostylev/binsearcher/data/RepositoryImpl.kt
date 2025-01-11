package ru.korostylev.binsearcher.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.korostylev.binsearcher.data.api.ApiFactory
import ru.korostylev.binsearcher.data.database.CardInfoDao
import ru.korostylev.binsearcher.data.database.CardInfoEntity
import ru.korostylev.binsearcher.domain.Bank
import ru.korostylev.binsearcher.domain.CardInfo
import ru.korostylev.binsearcher.domain.Country
import ru.korostylev.binsearcher.domain.Number
import ru.korostylev.binsearcher.domain.Repository

class RepositoryImpl(private val cardInfoDao: CardInfoDao): Repository {

    private val _cardInfo = MutableLiveData<CardInfo>()
    val cardInfo: LiveData<CardInfo>
        get() = _cardInfo
    private val mapper = CardInfoMapper()

    private val emptyCard = CardInfoEntity(
        0,
        Number(
            null,
            null
        ),
        "visa",
        "type",
        "brand",
        false,
        Country(
            16,
            "alpha",
            "name",
            "emoji",
            "rub",
            10,
            20
        ),
        Bank(
            "bank",
            null,
            null,
            "ekaterinburg"
        )


    )

    override suspend fun getCardInfo(bin: Int): LiveData<CardInfo> {
        try {
//            val card = ApiFactory.apiService.getCardInfo(bin).body()
//            card?.let {
//                _cardInfo.value = it
//                cardInfoDao.addCardInfo(mapper.mapToEntity(it))
//                Log.d("cardinfo", "value")
//            }
//            Log.d("cardinfo", card.toString())
            _cardInfo.postValue(mapper.mapFromEntity(emptyCard))
            cardInfoDao.addCardInfo(emptyCard)
            return cardInfo ?: throw RuntimeException("Error")
        } catch (e: Exception) {
            throw RuntimeException("Error")
        }
    }
}