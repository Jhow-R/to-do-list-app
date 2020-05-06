package br.com.fiap.listatarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var editTextTarefa: EditText
    lateinit var buttonAdd: Button
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTarefa = findViewById(R.id.editTextTarefa)
        buttonAdd = findViewById(R.id.buttonAdd)
        recyclerView = findViewById(R.id.recyclerView)

        val tarefaAdapter = TarefaAdapter()
        recyclerView.adapter = tarefaAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonAdd.setOnClickListener{
            val tarefa = Tarefa(
                descricao = editTextTarefa.text.toString(),
                concluida = false
            )

            tarefaAdapter.adicionarTarefa(tarefa)

        }
    }
}
