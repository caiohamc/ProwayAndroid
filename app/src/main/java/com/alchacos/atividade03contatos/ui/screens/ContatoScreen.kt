package com.alchacos.atividade03contatos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alchacos.atividade03contatos.viewmodel.ContatoViewModel

@Composable
fun ContactScreen(viewModel: ContatoViewModel) {
    val contatosSalvos by viewModel.contatos.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.weight(1.5f).verticalScroll(rememberScrollState())) {
            Text(text = if (viewModel.idEditando == null) "Novo Contato" else "Editando Contato",
                style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = viewModel.nome, onValueChange = { viewModel.nome = it },
                label = { Text("Nome Completo") }, modifier = Modifier.fillMaxWidth())

            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(value = viewModel.cep, onValueChange = { viewModel.onCepChange(it) },
                    label = { Text("CEP") }, modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                Spacer(Modifier.width(8.dp))
                OutlinedTextField(value = viewModel.numero, onValueChange = { viewModel.numero = it },
                    label = { Text("Nº") }, modifier = Modifier.weight(0.5f))
            }

            OutlinedTextField(value = viewModel.logradouro, onValueChange = { viewModel.logradouro = it },
                label = { Text("Logradouro") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = viewModel.bairro, onValueChange = { viewModel.bairro = it },
                label = { Text("Bairro") }, modifier = Modifier.fillMaxWidth())

            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(value = viewModel.cidade, onValueChange = { viewModel.cidade = it },
                    label = { Text("Cidade") }, modifier = Modifier.weight(2f))
                Spacer(Modifier.width(8.dp))
                OutlinedTextField(value = viewModel.estado, onValueChange = { viewModel.estado = it },
                    label = { Text("UF") }, modifier = Modifier.weight(1f))
            }

            Button(onClick = { viewModel.salvar() },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                Text(if (viewModel.idEditando == null) "Salvar Contato" else "Atualizar Contato")
            }

            if (viewModel.idEditando != null) {
                TextButton(onClick = { viewModel.salvar() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Cancelar", color = Color.Red)
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Text("Contatos", style = MaterialTheme.typography.titleLarge)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(contatosSalvos) { contato ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(contato.nome, fontWeight = FontWeight.Bold)
                            Text("${contato.logradouro}, ${contato.numero}",
                                style = MaterialTheme.typography.bodySmall)
                            Text("${contato.cidade} - ${contato.estado}",
                                style = MaterialTheme.typography.bodySmall)
                        }

                        IconButton(onClick = { viewModel.prepararEdicao(contato) }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar",
                                tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { viewModel.excluir(contato) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Excluir",
                                tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}