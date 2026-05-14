package com.alchacos.atividade03contatos.network

import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("https://viacep.com.br/ws/{cep}/json")
    suspend fun buscarEndereco(@Path("cep") cep: String): EnderecoResponse
}

data class EnderecoResponse(
    val logradouro: String? = "",
    val complemento: String? = "",
    val bairro: String? = "",
    val localidade: String? = "",
    val uf: String? = "",
    val estado: String? = ""
)