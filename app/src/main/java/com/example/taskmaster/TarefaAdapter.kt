package com.example.taskmaster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmaster.model.Tarefa

class TarefaAdapter(
    private var lista: List<Tarefa>,
    private val onItemClick: (Tarefa) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    inner class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.textTitulo)
        val descricao: TextView = itemView.findViewById(R.id.textDescricao)

        fun bind(tarefa: Tarefa) {
            titulo.text = tarefa.titulo
            descricao.text = tarefa.descricao

            itemView.setOnClickListener {
                onItemClick(tarefa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)
        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = lista[position]
        holder.bind(tarefa)
    }

    override fun getItemCount(): Int = lista.size

    fun atualizar(novaLista: List<Tarefa>) {
        lista = novaLista
        notifyDataSetChanged()
    }
}
