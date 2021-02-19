package br.com.mercadosallas.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ClienteDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("sobrenome")
    private String sobrenome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("email")
    private String email;

    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @JsonProperty("data_cadastro")
    private String dataCadastro;

    @JsonProperty("telefones")
    private List<TelefoneDto> telefones;


}
