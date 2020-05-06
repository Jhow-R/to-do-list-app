package br.com.fiap.listatarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter: RecyclerView.Adapter<TarefaViewHolder>() {

    private val items = mutableListOf<Tarefa>()

    fun adicionarTarefa(novaTarefa: Tarefa) {
        items.add(novaTarefa)

        // Notifica a lista que há novos items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TarefaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

}