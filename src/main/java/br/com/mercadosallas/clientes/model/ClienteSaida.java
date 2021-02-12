package br.com.mercadosallas.clientes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ClienteSaida implements Serializable {

    @JsonProperty("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
