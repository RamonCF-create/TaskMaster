package com.example.taskmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmaster.data.AppDatabase
import com.example.taskmaster.model.Tarefa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaTarefasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TarefaAdapter
    private lateinit var btnNovaTarefa: Button
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tarefas)

        db = AppDatabase.getDatabase(this)

        recyclerView = findViewById(R.id.recyclerView)
        btnNovaTarefa = findViewById(R.id.btnNovaTarefa)

        // Agora o adapter recebe um lambda para lidar com o clique no item
        adapter = TarefaAdapter(listOf()) { tarefa ->
            confirmarExclusao(tarefa)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnNovaTarefa.setOnClickListener {
            startActivity(Intent(this, NovaTarefaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        carregarTarefas()
    }

    private fun carregarTarefas() {
        lifecycleScope.launch {
            val tarefas = withContext(Dispatchers.IO) {
                db.tarefaDao().listar()
            }
            adapter.atualizar(tarefas)
        }
    }

    private fun confirmarExclusao(tarefa: Tarefa) {
        AlertDialog.Builder(this)
            .setTitle("Excluir tarefa")
            .setMessage("Tem certeza que deseja excluir esta tarefa?")
            .setPositiveButton("Sim") { _, _ ->
                excluirTarefa(tarefa)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun excluirTarefa(tarefa: Tarefa) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.tarefaDao().deletar(tarefa)
            withContext(Dispatchers.Main) {
                carregarTarefas()
            }
        }
    }
}
