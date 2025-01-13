package ru.korostylev.binsearcher.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.korostylev.binsearcher.domain.CardInfo

class CardListDiffCallback(): DiffUtil.ItemCallback<CardInfo>() {
    override fun areItemsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem == newItem
    }

}