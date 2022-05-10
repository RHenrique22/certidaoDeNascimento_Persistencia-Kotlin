package br.edu.ifpb.pdm.certidaodenascimento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.edu.ifpb.pdm.certidaodenascimento.dao.PessoaDAO
import br.edu.ifpb.pdm.certidaodenascimento.modelo.Pessoa

class MainActivity : AppCompatActivity() {

    private lateinit var edtNome:   EditText
    private lateinit var edtIdade:  EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnListar: Button
    private lateinit var banco:     PessoaDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.edtNome   = findViewById(R.id.inputNome)
        this.edtIdade  = findViewById(R.id.inputIdade)
        this.btnSalvar = findViewById(R.id.btnSalvar)
        this.btnListar = findViewById(R.id.btnListar)
        this.banco     = PessoaDAO(this)

        this.btnSalvar.setOnClickListener { salvar() }
        this.btnListar.setOnClickListener { listar() }

    }

    fun salvar() {
        val nome   = edtNome.text.toString()
        val idade  = edtIdade.text.toString().toInt()
        val pessoa = Pessoa(nome, idade)

        this.banco.insert(pessoa)
    }

    fun listar() {
        Log.i("APP_SQLITE", this.banco.select().toString())
    }
}