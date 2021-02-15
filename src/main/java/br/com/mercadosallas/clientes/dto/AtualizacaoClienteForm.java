package br.com.mercadosallas.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AtualizacaoClienteForm {
    @NotNull
    @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    private String sobrenome;

    @Email
    private String email;

    @JsonProperty("data_nascimento")
    private String dataNascimento;
}
