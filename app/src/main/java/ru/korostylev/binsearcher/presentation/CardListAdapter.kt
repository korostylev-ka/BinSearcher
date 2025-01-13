package ru.korostylev.binsearcher.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.korostylev.binsearcher.databinding.CardInfoBinding
import ru.korostylev.binsearcher.domain.CardInfo

class CardListAdapter:
    ListAdapter<CardInfo, CardInfoListViewHolder>(CardListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardInfoListViewHolder {
        val binding = CardInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardInfoListViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CardInfoListViewHolder, position: Int) {
        val cardInfo = getItem(position)
        holder.bind(cardInfo)
    }


}