package com.alchacos.atividade02despesaspessoais.models

data class Sonho(val id: Int,
                 val nome: String,
                 val valor: Double,
                 var dataRealizacao: String? = null,
                 var isRealizado: Boolean = false,
                 val isRealizavel: Boolean = false)