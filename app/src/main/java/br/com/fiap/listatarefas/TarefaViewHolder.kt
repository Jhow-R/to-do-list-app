package br.com.fiap.listatarefas

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textViewTarefa = view.findViewById<TextView>(R.id.textViewTarefa)
    private val checkBoxTarefa = view.findViewById<CheckBox>(R.id.checkBoxTarefa)

    fun bind(item: Tarefa) {

        checkBoxTarefa.setOnCheckedChangeListener{_, isChecked ->
            item.concluida = isChecked
        }

        textViewTarefa.text = item.descricao
        checkBoxTarefa.isChecked = item.concluida
    }
}