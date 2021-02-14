package br.com.mercadosallas.clientes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDto {

    @NotNull
    private String nome;
    @NotNull
    private String sobrenome;
    @NotNull
    private String cpf;
    private LocalDate dataNascimento;


}
