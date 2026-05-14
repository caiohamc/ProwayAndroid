package com.alchacos.atividade03contatos.repository

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

import com.alchacos.atividade03contatos.model.Contato

import kotlinx.coroutines.flow.Flow

@Dao
interface ContatoDao {
    @Query("SELECT * FROM contatos ORDER BY nome ASC")
    fun listar(): Flow<List<Contato>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(contato: Contato)

    @Delete
    suspend fun excluir(contato: Contato)

    @Update
    suspend fun atualizar(contato: Contato)
}

@Database(entities = [Contato::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}