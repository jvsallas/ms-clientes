package br.com.mercadosallas.clientes.dto;

import br.com.mercadosallas.telefones.dto.TelefoneForm;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ClienteForm {

    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    private String sobrenome;

    @NotNull @NotEmpty
    private String cpf;

    @JsonProperty("data_nascimento")
    private String dataNascimento;

    @NotEmpty @NotNull @Email
    private String email;

    @Valid @Size(min=1, max=5)
    private List<TelefoneForm> telefones;


}
