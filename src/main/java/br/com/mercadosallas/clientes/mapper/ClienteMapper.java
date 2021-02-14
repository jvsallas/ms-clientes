package br.com.mercadosallas.clientes.mapper;

import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.model.ClienteRequestDto;
import br.com.mercadosallas.clientes.model.ClienteResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteEntity mapToEntity(ClienteRequestDto clienteRequestDto){

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteRequestDto.getNome());
        clienteEntity.setSobrenome(clienteRequestDto.getSobrenome());
        clienteEntity.setCpf(clienteRequestDto.getCpf());
        clienteEntity.setDataNascimento(clienteRequestDto.getDataNascimento());

        return clienteEntity;
    }

}
