package br.com.mercadosallas.clientes.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class ClienteResponseDto {

    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;

    public ClienteResponseDto(ClienteEntity clienteEntity){
        this.id = clienteEntity.getId();
        this.nome = clienteEntity.getNome();
        this.sobrenome = clienteEntity.getSobrenome();
        this.cpf = clienteEntity.getCpf();
        this.dataNascimento = clienteEntity.getDataNascimento();
    }

    public static List<ClienteResponseDto> converter(List<ClienteEntity> clientes){
        return clientes.stream().map(ClienteResponseDto::new).collect(Collectors.toList());
    }

}
