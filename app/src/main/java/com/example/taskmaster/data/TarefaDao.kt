package com.example.taskmaster.data

import androidx.room.*
import com.example.taskmaster.model.Tarefa

@Dao
interface TarefaDao {
    @Query("SELECT * FROM tarefas")
    suspend fun listar(): List<Tarefa>

    @Insert
    suspend fun inserir(tarefa: Tarefa)

    @Delete
    suspend fun deletar(tarefa: Tarefa)
}
