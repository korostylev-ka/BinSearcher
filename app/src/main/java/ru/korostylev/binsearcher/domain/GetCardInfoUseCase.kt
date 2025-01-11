package ru.korostylev.binsearcher.domain

class GetCardInfoUseCase(private val repository: Repository) {

    suspend operator fun invoke(bin: Int) = repository.getCardInfo(bin)

}