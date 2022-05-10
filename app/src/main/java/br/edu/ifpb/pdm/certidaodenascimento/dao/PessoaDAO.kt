package br.edu.ifpb.pdm.certidaodenascimento.dao

import android.content.ContentValues
import android.content.Context
import br.edu.ifpb.pdm.certidaodenascimento.modelo.Pessoa
import java.util.ArrayList

class PessoaDAO(var context: Context) {
    var banco: BancoHelper

    init {
        this.banco = BancoHelper(context)
    }

    fun insert(pessoa: Pessoa) {
        val insert = ContentValues()
        insert.put("nome",  pessoa.nome)
        insert.put("idade", pessoa.idade)

        this.banco.writableDatabase.insert(
            "PESSOAS"
            ,null
            ,insert
        )
    }

    fun select(): ArrayList<Pessoa> {
        var lista   = ArrayList<Pessoa>()
        var colunas = arrayOf("id", "nome", "idade")

        val cursor  = this.banco.readableDatabase.query(
            "PESSOAS"
            ,colunas
            ,null
            ,null
            ,null
            ,null
            ,null
        )

        cursor.moveToFirst()

        for (i in 0 until cursor.count) {
            var id    = cursor.getInt(colunas.indexOf("id"))
            var nome  = cursor.getString(colunas.indexOf("nome"))
            var idade = cursor.getInt(colunas.indexOf("idade"))

            lista.add(Pessoa(id, nome, idade))
            cursor.moveToNext()
        }

        return lista
    }

    fun find(id: Int): Pessoa? {
        var colunas    = arrayOf("id", "nome", "idade")
        val where      = "id = ?"
        val whereValue = arrayOf(id.toString())

        val cursor     = this.banco.readableDatabase.query(
            "PESSOAS"
            ,colunas
            ,where
            ,whereValue
            ,null
            ,null
            ,null
        )
        cursor.moveToFirst()

        if (cursor.count == 1) {
            val id    = cursor.getInt(colunas.indexOf("id"))
            val nome  = cursor.getString(colunas.indexOf("nome"))
            val idade = cursor.getInt(colunas.indexOf("idade"))

            return Pessoa(id, nome, idade)
        }

        return null
    }

    fun count(): Int {
        val count  = "SELECT COUNT(P.ID) FROM PESSOAS P"
        val cursor = this.banco.readableDatabase.rawQuery(
            count
            ,null
        )
        cursor.moveToFirst()

        return cursor.getInt(0)
    }

    fun update(pessoa: Pessoa) {
        val where      = "id = ?"
        val whereValue = arrayOf(pessoa.id.toString())
        val update     = ContentValues()
        update.put("nome",  pessoa.nome)
        update.put("idade", pessoa.idade)

        this.banco.writableDatabase.update(
            "PESSOAS"
            ,update
            ,where
            ,whereValue
        )
    }

    fun delete(id: Int) {
        val where      = "id = ?"
        val whereValue = arrayOf(id.toString())

        this.banco.writableDatabase.delete(
            "PESSOAS"
            ,where
            ,whereValue
        )
    }
}