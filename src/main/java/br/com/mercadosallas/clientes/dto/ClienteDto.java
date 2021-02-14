package br.com.mercadosallas.clientes.dto;

import br.com.mercadosallas.clientes.model.ClienteEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class ClienteDto {

    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;


    public ClienteDto(ClienteEntity clienteEntity){
        this.id = clienteEntity.getId();
        this.nome = clienteEntity.getNome();
        this.sobrenome = clienteEntity.getSobrenome();
        this.cpf = clienteEntity.getCpf();
        this.dataNascimento = clienteEntity.getDataNascimento();
        this.dataCadastro = clienteEntity.getDataCadastro();
    }

    public static List<ClienteDto> converter(List<ClienteEntity> clientes){
        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }

}
