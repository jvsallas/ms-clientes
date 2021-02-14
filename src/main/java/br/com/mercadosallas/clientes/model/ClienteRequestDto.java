package br.com.mercadosallas.clientes.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClienteRequestDto {

    @NotNull
    private String nome;
    @NotNull
    private String sobrenome;
    @NotNull
    private String cpf;
    private LocalDate dataNascimento;


}
