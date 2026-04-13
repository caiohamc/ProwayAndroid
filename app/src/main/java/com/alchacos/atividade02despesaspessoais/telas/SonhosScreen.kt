package com.alchacos.atividade02despesaspessoais.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alchacos.atividade02despesaspessoais.models.Sonho

@Composable
fun SonhosScreen(
    sonhos: List<Sonho>,
    saldo: Double,
    onAddSonho: (Sonho) -> Unit,
    onDeleteSonho: (Sonho) -> Unit
) {
    // Scaffold interno para ter FAB exclusivo desta tela
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Exemplo de adição rápida (em um app real você abriria um Dialog)
                    val novoId = (sonhos.maxOfOrNull { it.id } ?: 0) + 1
                    onAddSonho(Sonho(novoId, "Novo Sonho $novoId", 100.0))
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Sonho")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "SONHOS",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (sonhos.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Não existem sonhos cadastrados ainda.",
                        color = Color.Gray, textAlign = TextAlign.Center)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(sonhos) { sonho -> SonhoItem(sonho, saldo, onDeleteSonho) }
                }
            }
        }
    }
}

@Composable
fun SonhoItem(sonho: Sonho, saldo: Double, onDeleteSonho: (Sonho) -> Unit) {
    val corBola = when {
        sonho.isRealizado -> Color.Black
        saldo >= sonho.valor -> Color(0xFF2E7D32)
        saldo >= sonho.valor * 0.7 -> Color.Blue
        else -> Color(0xFFD32F2F)
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(16.dp).background(corBola, CircleShape))
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(sonho.nome, fontWeight = FontWeight.Bold)
                Text("R$ ${String.format("%.2f", sonho.valor)}", fontSize = 14.sp)
            }

            if (!sonho.isRealizado) {
                IconButton(onClick = { /* Lógica editar */ }) {
                    Icon(Icons.Default.Edit, null, tint = Color.Gray)
                }
                IconButton(onClick = { onDeleteSonho(sonho) }) {
                    Icon(Icons.Default.Delete, null, tint = Color.Red)
                }
            }
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
    }
}