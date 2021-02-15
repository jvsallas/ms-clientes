package br.com.mercadosallas.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ClienteForm {

    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    private String sobrenome;

    @NotNull @NotEmpty
    private String cpf;

    @Email
    private String email;

    @JsonProperty("data_nascimento")
    private String dataNascimento;

}
