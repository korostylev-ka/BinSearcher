package ru.korostylev.binsearcher.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.korostylev.binsearcher.data.RepositoryImpl
import ru.korostylev.binsearcher.data.database.AppDatabase
import ru.korostylev.binsearcher.domain.CardInfo
import ru.korostylev.binsearcher.domain.GetCardInfoUseCase

class CardInfoViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(AppDatabase.newInstance(application).cardInfoDao())

    private val getCardInfo = GetCardInfoUseCase(repository)

    private val _cardInfo = MutableLiveData<CardInfo>()
    val cardInfo: LiveData<CardInfo>
        get() = _cardInfo

    fun getCardInfo(bin: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val cardInfoLD = getCardInfo.invoke(bin)
            _cardInfo.postValue(cardInfoLD.value)
        }
    }


}