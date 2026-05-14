package com.alchacos.atividade03contatos.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alchacos.atividade03contatos.model.Contato
import com.alchacos.atividade03contatos.repository.ContatoRepository
import kotlinx.coroutines.launch

class ContatoViewModel(private val repository: ContatoRepository) : ViewModel() {
    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var telefone by mutableStateOf("")
    var nascimento by mutableStateOf("")
    var cep by mutableStateOf("")
    var bairro by mutableStateOf("")
    var logradouro by mutableStateOf("")
    var numero by mutableStateOf("")
    var estado by mutableStateOf("")
    var cidade by mutableStateOf("")

    val contatos = repository.contatos

    var idEditando by mutableStateOf<Int?>(null)

    fun onCepChange(novoCep: String) {
        if (novoCep.length <= 8) cep = novoCep

        if (novoCep.length == 8) {
            viewModelScope.launch {
                try {
                    val dados = repository.buscarCep(novoCep)
                    logradouro = dados.logradouro ?: ""
                    bairro = dados.bairro ?: ""
                    cidade = dados.localidade ?: ""
                    estado = dados.uf ?: ""
                } catch (e: Exception) {
                    // Erro de conexão ou CEP inválido
                }
            }
        }
    }

    fun excluir(contato: Contato) {
        viewModelScope.launch {
            repository.excluir(contato)
        }
    }

    fun prepararEdicao(contato: Contato) {
        idEditando = contato.id
        nome = contato.nome
        email = contato.email
        telefone = contato.telefone
        nascimento = contato.nascimento
        cep = contato.cep
        bairro = contato.bairro
        logradouro = contato.logradouro
        numero = contato.numero
        estado = contato.estado
        cidade = contato.cidade
    }

    fun salvar() {
        if (nome.isBlank() || cep.length < 8) return

        viewModelScope.launch {
            val contato = Contato(id = idEditando ?: 0, nome = nome, email = email,
                telefone = telefone, nascimento = nascimento, cep = cep, bairro = bairro,
                logradouro = logradouro, numero = numero, estado = estado, cidade = cidade)

            if (idEditando == null)
                repository.salvar(contato)
            else
                repository.atualizar(contato)

            limparFormulario()
        }
    }

    private fun limparFormulario() {
        idEditando = null
        nome = ""; email = ""; telefone = ""; nascimento = ""; cep = ""
        bairro = ""; logradouro = ""; numero = ""; estado = ""; cidade = ""
    }
}

class ContatoViewModelFactory(private val repository: ContatoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContatoViewModel(repository) as T
    }
}