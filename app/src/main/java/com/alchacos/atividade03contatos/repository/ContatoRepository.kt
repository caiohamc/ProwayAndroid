package com.alchacos.atividade03contatos.repository

import com.alchacos.atividade03contatos.network.RetrofitService
import com.alchacos.atividade03contatos.model.Contato

class ContatoRepository(private val dao: ContatoDao, private val api: RetrofitService) {
    val contatos = dao.listar()
    suspend fun salvar(contato: Contato) = dao.inserir(contato)
    suspend fun excluir(contato: Contato) = dao.excluir(contato)
    suspend fun atualizar(contato: Contato) = dao.atualizar(contato)

    suspend fun buscarCep(cep: String) = api.buscarEndereco(cep)
}