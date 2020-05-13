package br.com.fiap.listatarefas.database

import android.content.ContentValues
import br.com.fiap.listatarefas.Tarefa

fun TarefasDatabase.listarTarefas(): List<Tarefa>? {

    val sql = """
        SELECT * FROM TBL_TAREFAS
    """.trimIndent();

    val cursor = this.readableDatabase.rawQuery(sql, null)

    val resultados = mutableListOf<Tarefa>()

    while (cursor.moveToNext()) {
        val idTarefa = cursor.getInt(cursor.getColumnIndex("_id"))
        val descricao = cursor.getString(cursor.getColumnIndex("descricao"))
        val flagConcluida = cursor.getInt(cursor.getColumnIndex("flag_concluida"))

        val tarefa = Tarefa(
            idTarefa = idTarefa,
            descricao = descricao,
            concluida = flagConcluida == 1
        )

        resultados.add(tarefa)

    }

    cursor.close()

    return resultados;
}

fun TarefasDatabase.inserirTarefa(item: Tarefa): Long {
    return this.writableDatabase.insert("TBL_TAREFAS", null, ContentValues().apply {
        put("descricao", item.descricao)
        put("flag_concluida", if (item.concluida) 1 else 0)
    })
}

fun TarefasDatabase.removerTarefa(idTarefa: Int?): Int {
    return this.writableDatabase.delete("TBL_TAREFAS", "_id = $idTarefa", null)
}

fun TarefasDatabase.atualizarTarefa(idTarefa: Int?, concluida: Boolean): Int {

    val valores = ContentValues().apply {
        put("flag_concluida", if (concluida) 1 else 0)
    }

    return this.writableDatabase.update(
        "TBL_TAREFAS",
        valores,
        "_id = $idTarefa", null
    )
}

