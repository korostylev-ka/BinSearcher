package ru.korostylev.binsearcher.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.korostylev.binsearcher.data.api.ApiService
import ru.korostylev.binsearcher.data.database.CardInfoDao
import ru.korostylev.binsearcher.domain.CardInfo
import ru.korostylev.binsearcher.domain.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val cardInfoDao: CardInfoDao,
    private val apiService: ApiService
): Repository {

    private val _cardInfo = MutableLiveData<CardInfo>()
    val cardInfo: LiveData<CardInfo>
        get() = _cardInfo
    private val _listCardInfoLD = MutableLiveData<List<CardInfo>>()
    val listCardInfoLD: LiveData<List<CardInfo>>
        get() = _listCardInfoLD
    private val mapper = CardInfoMapper()
    private val emptyListCardInfo: List<CardInfo> = emptyList()

    override suspend fun getCardInfo(bin: Int): LiveData<CardInfo> {
        try {
            val card = apiService.getCardInfo(bin).body()
            card?.let {
                _cardInfo.postValue(it.copy(bin = bin))
                cardInfoDao.addCardInfo(mapper.mapToEntity(it.copy(bin = bin)))
            }
            Log.d("cardinfo", card.toString())
            return cardInfo
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }

    override fun getListCardInfo(): LiveData<List<CardInfo>> {
        try {
            val list = cardInfoDao.getCardInfoList().map {
                mapper.mapListFromEntity(it)
            }
            _listCardInfoLD.value = list.value
            return list

        } catch (e: RuntimeException) {
            return _listCardInfoLD
        }
    }
}