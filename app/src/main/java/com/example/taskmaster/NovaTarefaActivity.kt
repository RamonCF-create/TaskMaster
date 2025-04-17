package com.example.taskmaster

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.taskmaster.data.AppDatabase
import com.example.taskmaster.model.Tarefa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovaTarefaActivity : AppCompatActivity() {

    private lateinit var titulo: EditText
    private lateinit var descricao: EditText
    private lateinit var btnSalvar: Button
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_tarefa)

        titulo = findViewById(R.id.editTitulo)
        descricao = findViewById(R.id.editDescricao)
        btnSalvar = findViewById(R.id.btnSalvar)

        db = AppDatabase.getDatabase(this)

        btnSalvar.setOnClickListener {
            val novaTarefa = Tarefa(titulo = titulo.text.toString(), descricao = descricao.text.toString())

            lifecycleScope.launch(Dispatchers.IO) {
                db.tarefaDao().inserir(novaTarefa)
                runOnUiThread {
                    Toast.makeText(this@NovaTarefaActivity, "Tarefa salva!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
