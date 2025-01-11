package ru.korostylev.binsearcher.data

import ru.korostylev.binsearcher.data.database.CardInfoEntity
import ru.korostylev.binsearcher.domain.CardInfo

class CardInfoMapper {

    fun mapToEntity(cardInfo: CardInfo): CardInfoEntity {
        return CardInfoEntity(
            id = -1,
            number = cardInfo.number,
            scheme = cardInfo.scheme,
            type = cardInfo.type,
            brand = cardInfo.brand,
            prepaid = cardInfo.prepaid,
            country = cardInfo.country,
            bank = cardInfo.bank
        )
    }

    fun mapFromEntity(cardInfoEntity: CardInfoEntity): CardInfo {
        return CardInfo(
            number = cardInfoEntity.number,
            scheme = cardInfoEntity.scheme,
            type = cardInfoEntity.type,
            brand = cardInfoEntity.brand,
            prepaid = cardInfoEntity.prepaid,
            country = cardInfoEntity.country,
            bank = cardInfoEntity.bank
        )
    }
}