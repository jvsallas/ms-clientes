package br.com.mercadosallas.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClienteAtualizacaoForm {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("sobrenome")
    private String sobrenome;

    @JsonProperty("data_nascimento")
    private String dataNascimento;
}
