package br.edu.ifpb.pdm.certidaodenascimento.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(var context: Context): SQLiteOpenHelper(
    context
    ,"pessoasqlite"
    ,null
    ,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreate = " CREATE TABLE PESSOAS ("                  +
                        " ID    INTEGER PRIMARY KEY AUTOINCREMENT" +
                        ",NOME  TEXT"                              +
                        ",IDADE INTEGER)"

        db?.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion != oldVersion) {
            db?.execSQL("DROP TABLE PESSOAS")
            this.onCreate(db)
        }
    }

}