package ru.korostylev.binsearcher.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.korostylev.binsearcher.domain.CardInfo

interface ApiService {

    @GET("/{bin}")
    suspend fun getCardInfo(@Path("bin") bin: Int): Response<CardInfo>

}