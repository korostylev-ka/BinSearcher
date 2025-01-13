package ru.korostylev.binsearcher.presentation

import android.content.Context
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.korostylev.binsearcher.R
import ru.korostylev.binsearcher.databinding.CardInfoBinding
import ru.korostylev.binsearcher.domain.CardInfo

class CardInfoListViewHolder(
    private val binding: CardInfoBinding,
    @ApplicationContext
    private val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cardInfo: CardInfo) {
        with(binding) {
            binValueTv.text = cardInfo.bin.toString()
            lengthValueTv.text = cardInfo.number.length?.toString() ?: getString(context, R.string.no_data)
            luhnValueTv.text = cardInfo.number.luhn?.toString() ?: getString(context, R.string.no_data)
            schemeValueTv.text = cardInfo.scheme ?: getString(context, R.string.no_data)
            typeValueTv.text = cardInfo.type ?: getString(context, R.string.no_data)
            brandValueTv.text = cardInfo.brand ?: getString(context, R.string.no_data)
            prepaidValueTv.text = cardInfo.prepaid?.toString() ?: getString(context, R.string.no_data)
            numericValueTv.text = cardInfo.country.numeric?.toString() ?: getString(context, R.string.no_data)
            alpha2ValueTv.text = cardInfo.country.alpha2 ?: getString(context, R.string.no_data)
            nameValueTv.text = cardInfo.country.name ?: getString(context, R.string.no_data)
            emojiValueTv.text = cardInfo.country.emoji ?: getString(context, R.string.no_data)
            currencyValueTv.text = cardInfo.country.currency ?: getString(context, R.string.no_data)
            latitudeValueTv.text = cardInfo.country.latitude?.toString() ?: getString(context, R.string.no_data)
            longtitudeValueTv.text = cardInfo.country.longitude?.toString() ?: getString(context, R.string.no_data)
            bankNameValueTv.text = cardInfo.bank.name ?: getString(context, R.string.no_data)
            urlValueTv.text = cardInfo.bank.url ?: getString(context, R.string.no_data)
            phoneValueTv.text = cardInfo.bank.phone ?: getString(context, R.string.no_data)
            cityValueTv.text = cardInfo.bank.city ?: getString(context, R.string.no_data)
        }
    }
}