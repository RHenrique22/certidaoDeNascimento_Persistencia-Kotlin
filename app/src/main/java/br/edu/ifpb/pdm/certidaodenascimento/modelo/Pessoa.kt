package br.edu.ifpb.pdm.certidaodenascimento.modelo

class Pessoa {
    var id:    Int
    var nome:  String
    var idade: Int

    constructor(nome: String, idade: Int) {
        this.id    = -1
        this.nome  = nome
        this.idade = idade
    }

    constructor(id: Int, nome: String, idade: Int) {
        this.id    = id
        this.nome  = nome
        this.idade = idade
    }

    override fun toString(): String {
        return "ID: ${this.id} - Nome: ${this.nome} - Idade: ${this.idade}"
    }

}