package br.com.mercadosallas.clientes.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClienteForm {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String sobrenome;
    @NotNull @NotEmpty
    private String cpf;
    private LocalDate dataNascimento;


}
