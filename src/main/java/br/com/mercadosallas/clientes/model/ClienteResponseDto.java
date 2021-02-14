package br.com.mercadosallas.clientes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDto {

    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;

}
