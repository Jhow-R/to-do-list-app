package br.com.fiap.listatarefas

data class Tarefa(
    val idTarefa: Int? = null,
    val descricao: String,
    var concluida: Boolean,
    var onUpdate: ((Tarefa)-> Unit)? = null
)