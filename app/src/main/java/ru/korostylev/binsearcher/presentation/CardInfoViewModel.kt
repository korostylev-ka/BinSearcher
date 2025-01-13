package ru.korostylev.binsearcher.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.korostylev.binsearcher.domain.CardInfo
import ru.korostylev.binsearcher.domain.GetCardInfoUseCase
import ru.korostylev.binsearcher.domain.GetListCardInfoUseCase
import ru.korostylev.binsearcher.domain.Repository
import javax.inject.Inject

@HiltViewModel
class CardInfoViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val getCardInfo = GetCardInfoUseCase(repository)
    private val getListCardInfo = GetListCardInfoUseCase(repository)
    private val _cardInfo = MutableLiveData<CardInfo>()
    val cardInfo: LiveData<CardInfo>
        get() = _cardInfo
    private val _listCardInfoLD = MutableLiveData<List<CardInfo>>()
    val listCardInfoLD: LiveData<List<CardInfo>>
        get() = getListCardInfo.invoke()

    fun getCardInfo(bin: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val cardInfoLD = getCardInfo.invoke(bin)
            _cardInfo.postValue(cardInfoLD.value)
        }
    }

}