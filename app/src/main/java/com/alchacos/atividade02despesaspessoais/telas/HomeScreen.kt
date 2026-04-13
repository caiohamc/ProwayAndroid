package com.alchacos.atividade02despesaspessoais.telas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun HomeScreen(ganhosTotal: Double, despesasTotal: Double, saldo: Double,
    sonhosNaoRealizados: List<Sonho>, sonhosRealizados: List<Sonho>, onRealizarSonho: (Sonho) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "BRADESpesas", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow(Icons.Default.KeyboardArrowUp, "Ganhos", ganhosTotal, Color.Black)
                InfoRow(Icons.Default.KeyboardArrowDown, "Despesas", despesasTotal, Color.Black)
                val corSaldo = if (saldo >= 0) Color(0xFF2E7D32) else Color(0xFFD32F2F)
                InfoRow(Icons.Default.Info, "Saldo", saldo, corSaldo)
            }
        }

        LazyColumn {
            item {
                Text("Sonhos Não Realizados", fontSize = 20.sp, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), textAlign = TextAlign.Center)
            }
            items(sonhosNaoRealizados.windowed(2, 2, true)) { pair ->
                Row(Modifier.fillMaxWidth()) {
                    pair.forEach { sonho -> SonhoHomeCard(sonho, saldo, Modifier.weight(1f), onRealizarSonho) }
                    if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
            }
            item {
                Text("Sonhos Realizados", fontSize = 20.sp, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), textAlign = TextAlign.Center)
            }
            items(sonhosRealizados.windowed(2, 2, true)) { pair ->
                Row(Modifier.fillMaxWidth()) {
                    pair.forEach { sonho -> SonhoHomeCard(sonho, saldo, Modifier.weight(1f), {}, isRealizado = true) }
                    if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, valor: Double, corValor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("$label: ", fontWeight = FontWeight.Medium)
        Text("R$${String.format("%.2f", valor)}", color = corValor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SonhoHomeCard(sonho: Sonho, saldo: Double, modifier: Modifier, onRealizar: (Sonho) -> Unit, isRealizado: Boolean = false) {
    Card(modifier = modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)) {
        Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(sonho.nome, fontWeight = FontWeight.Bold)
            Text("R$${sonho.valor}")
            if (!isRealizado && saldo >= sonho.valor) {
                Button(onClick = { onRealizar(sonho) }, modifier = Modifier.padding(top = 4.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)) {
                    Text("Realizar sonho", fontSize = 10.sp)
                }
            }
        }
    }
}