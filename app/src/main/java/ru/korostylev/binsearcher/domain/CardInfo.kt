package ru.korostylev.binsearcher.domain

data class CardInfo(
    val number: Number,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country,
    val bank: Bank
)