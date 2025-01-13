package ru.korostylev.binsearcher.domain

data class CardInfo(
    val id: Int,
    val bin: Int,
    val number: Number,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country,
    val bank: Bank
)