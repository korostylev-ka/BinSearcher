package ru.korostylev.binsearcher.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.korostylev.binsearcher.domain.Bank
import ru.korostylev.binsearcher.domain.Country
import ru.korostylev.binsearcher.domain.Number

@Entity
data class CardInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bin: Int,
    @Embedded
    val number: Number,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded(prefix = "country")
    val country: Country,
    @Embedded(prefix = "bank")
    val bank: Bank
) {
    companion object {

         const val UNDEFINED_ID = 0
    }
}