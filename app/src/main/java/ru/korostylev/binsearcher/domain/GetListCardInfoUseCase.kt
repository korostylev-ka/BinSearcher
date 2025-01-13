package ru.korostylev.binsearcher.domain

class GetListCardInfoUseCase(private val repository: Repository) {

    operator fun invoke() = repository.getListCardInfo()
}