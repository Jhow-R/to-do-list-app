package br.com.fiap.listatarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.listatarefas.database.TarefasDatabase
import br.com.fiap.listatarefas.database.atualizarTarefa
import br.com.fiap.listatarefas.database.inserirTarefa
import br.com.fiap.listatarefas.database.listarTarefas

class MainActivity : AppCompatActivity() {

    lateinit var editTextTarefa: EditText
    lateinit var buttonAdd: Button
    lateinit var recyclerView: RecyclerView
    lateinit var database: TarefasDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTarefa = findViewById(R.id.editTextTarefa)
        buttonAdd = findViewById(R.id.buttonAdd)
        recyclerView = findViewById(R.id.recyclerView)

        database = TarefasDatabase(this)

        val tarefaAdapter = TarefaAdapter()

        //Configurar RecyclerView
        recyclerView.adapter = tarefaAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        database.listarTarefas()?.forEach {
            // Passar a referência da função
            it.onUpdate = ::atualizarTarefa
            tarefaAdapter.adicionarTarefa(it)
        }

        buttonAdd.setOnClickListener {

            val tarefa = Tarefa(
                descricao = editTextTarefa.text.toString(),
                concluida = false,
                onUpdate = ::atualizarTarefa
            )

            val idTarefa = database.inserirTarefa(tarefa)

            if (idTarefa != -1L) {
                tarefaAdapter.adicionarTarefa(tarefa.copy(idTarefa = idTarefa.toInt()))
            } else {
                Toast.makeText(this, "Erro ao inserir", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun atualizarTarefa(item: Tarefa) {
        database.atualizarTarefa(item.idTarefa, item.concluida)
    }
}