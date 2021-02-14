package br.com.mercadosallas.clientes.mapper;

import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.model.ClienteRequestDto;
import br.com.mercadosallas.clientes.model.ClienteResponseDto;

import java.util.UUID;

public class ClienteMapper {

    public static ClienteEntity mapToEntity(ClienteRequestDto clienteRequestDto){

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteRequestDto.getNome());
        clienteEntity.setSobrenome(clienteRequestDto.getSobrenome());
        clienteEntity.setCpf(clienteRequestDto.getCpf());
        clienteEntity.setDataNascimento(clienteRequestDto.getDataNascimento());

        return clienteEntity;
    }

    public static ClienteResponseDto mapToResponse(ClienteEntity clienteEntity){

        ClienteResponseDto clienteResponseDto = new ClienteResponseDto();
        clienteResponseDto.setId(clienteEntity.getId());
        clienteResponseDto.setNome(clienteEntity.getNome());
        clienteResponseDto.setSobrenome(clienteEntity.getSobrenome());
        clienteResponseDto.setCpf(clienteEntity.getCpf());
        clienteResponseDto.setDataNascimento(clienteEntity.getDataNascimento());

        return clienteResponseDto;
    }

}
