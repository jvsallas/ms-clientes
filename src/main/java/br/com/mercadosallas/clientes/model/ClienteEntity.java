package br.com.mercadosallas.clientes.model;

import java.io.Serializable;


public class ClienteEntity implements Serializable {

    private String id;
    private String nome;
    private String cpf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
