package br.com.fiap.listatarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TarefasDatabase(
    context: Context
) : SQLiteOpenHelper (context, "tarefas.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            CREATE TABLE TBL_TAREFAS(
                _id INTEGER PRIMARY KEY autoincrement,
                descricao TEXT,
                flag_concluida INTEGER
            )
        """.trimIndent()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS TBL_TAREFAS;")
        onCreate(db)
    }
}