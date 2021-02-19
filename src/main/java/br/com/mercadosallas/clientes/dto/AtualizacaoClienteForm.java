package br.com.mercadosallas.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AtualizacaoClienteForm {

    @JsonProperty("nome")
    private String nome;
    @JsonProperty("sobrenome")
    private String sobrenome;
    @JsonProperty("email")
    private String email;
    @JsonProperty("data_nascimento")
    private String dataNascimento;
}
