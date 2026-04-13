package com.alchacos.atividade02despesaspessoais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alchacos.atividade02despesaspessoais.models.Movimentacao
import com.alchacos.atividade02despesaspessoais.models.Sonho
import com.alchacos.atividade02despesaspessoais.telas.DespesasScreen
import com.alchacos.atividade02despesaspessoais.telas.GanhosScreen
import com.alchacos.atividade02despesaspessoais.telas.HomeScreen
import com.alchacos.atividade02despesaspessoais.telas.SonhosScreen
import com.alchacos.atividade02despesaspessoais.telas.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BradespesasApp()
        }
    }
}

@Composable
fun BradespesasApp() {
    val navController = rememberNavController()

    val listaGanhos = remember {
        mutableStateListOf(
            Movimentacao("Salário", 8120.0, "30/03/2026"),
            Movimentacao("Dividendos", 180.0, "02/04/2026"),
            Movimentacao("Venda", 540.0, "05/04/2026"),
            Movimentacao("Reembolso", 790.0, "07/04/2026")
        )
    }

    val listaSonhos = remember { mutableStateListOf<Sonho>() }
    val listaSonhos = remember {
        mutableStateListOf(
            Sonho(1, "Jantar no Coco Bambu", 220.0),
            Sonho(2, "Teclado Logitech", 635.0),
            Sonho(3, "Machu Piccu", 5320.0),
            Sonho(4, "Australia", 9700.0),
            Sonho(5, "Mouse Logitech", 320.0),
            Sonho(6, "Kit de Camisas Long", 345.0),
            Sonho(7, "Suplementação", 185.0),
            Sonho(8, "Moto", 35000.0),
            Sonho(9, "Sonho 14", 3300.0),
            Sonho(10, "Carro", 85000.0),
            Sonho(11, "WWW", 1000.0),
            Sonho(12, "XXX", 2000.0),
            Sonho(13, "YYY", 3000.0),
            Sonho(14, "ZZZ", 4000.0),
            Sonho(15, "Celular", 4800.0, isRealizado = true, isRealizavel = true, dataRealizacao = "05/04/2026"),
            Sonho(16, "Presente Esposa", 215.0, isRealizado = true, isRealizavel = true, dataRealizacao = "04/04/2026"),
            Sonho(17, "Tênis", 312.0, isRealizado = true, isRealizavel = true, dataRealizacao = "04/04/2026"),
            Sonho(18, "Perfume", 438.0, isRealizado = true, isRealizavel = true, dataRealizacao = "04/04/2026")
        )
    }

    val totalGanhos = listaGanhos.sumOf { it.valor }
    val totalDespesas = listaSonhos.filter { it.isRealizado }.sumOf { it.valor }
    val saldoAtual = totalGanhos - totalDespesas

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            if (currentDestination?.route != "splash") {
                NavigationBar {
                    val items = listOf(
                        Triple("Home", "Home", Icons.Default.Home),
                        Triple("Ganhos", "Ganhos", Icons.Default.KeyboardArrowUp),
                        Triple("Despesas", "Despesas", Icons.Default.KeyboardArrowDown),
                        Triple("Sonhos", "Sonhos", Icons.Default.Star))

                    items.forEach { (route, label, icon) ->
                        NavigationBarItem(
                            icon = { Icon(icon, contentDescription = null) },
                            label = { Text(label) },
                            selected = currentDestination?.hierarchy?.any { it.route == route } == true,
                            onClick = {
                                navController.navigate(route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "splash",
            modifier = Modifier.padding(innerPadding)) {
            composable("splash") { SplashScreen(navController) }
            composable("home") {
                HomeScreen(
                    ganhosTotal = totalGanhos,
                    despesasTotal = totalDespesas,
                    saldo = saldoAtual,
                    sonhosNaoRealizados = listaSonhos.filter { !it.isRealizado },
                    sonhosRealizados = listaSonhos.filter { it.isRealizado },
                    onRealizarSonho = { sonhoAlvo : Sonho ->
                        // Lógica de "Realizar Sonho"
                        val index = listaSonhos.indexOfFirst { it.id == sonhoAlvo.id }
                        if (index != -1) {
                            listaSonhos[index] = listaSonhos[index].copy(
                                isRealizado = true, dataRealizacao = "08/04/2026")
                        }
                    }
                )
            }
            composable("ganhos") { GanhosScreen(listaGanhos) }
            composable("despesas") { DespesasScreen(listaSonhos.filter { it.isRealizado }) }
            composable("sonhos") {
                SonhosScreen(sonhos = listaSonhos, saldo = saldoAtual,
                    onAddSonho = { novoSonho -> listaSonhos.add(novoSonho) },
                    onDeleteSonho = { sonho -> listaSonhos.remove(sonho) }
                )
            }
        }
    }
}