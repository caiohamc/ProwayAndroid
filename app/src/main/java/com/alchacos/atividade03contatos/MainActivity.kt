package com.alchacos.atividade03contatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.alchacos.atividade03contatos.network.RetrofitService
import com.alchacos.atividade03contatos.repository.ContatoRepository
import androidx.compose.material3.MaterialTheme
import com.alchacos.atividade03contatos.repository.AppDatabase
import com.alchacos.atividade03contatos.ui.screens.ContactScreen
import com.alchacos.atividade03contatos.viewmodel.ContatoViewModelFactory
import com.alchacos.atividade03contatos.viewmodel.ContatoViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "contatos_database")
                .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(RetrofitService::class.java)

        val repository = ContatoRepository(db.contatoDao(), apiService)
        val factory = ContatoViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ContatoViewModel::class.java]

        setContent { MaterialTheme { ContactScreen(viewModel) } }
    }
}