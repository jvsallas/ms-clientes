package br.com.mercadosallas.clientes.dto;

import br.com.mercadosallas.telefones.dto.TelefoneForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteAtualizacaoPutForm {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String sobrenome;

    private String cpf;

    @NotNull
    @NotEmpty
    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @Email
    private String email;

    @Valid
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 5)
    private List<TelefoneForm> telefones;

}
