package br.com.mercadosallas.clientes.mapper;

import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.dto.ClienteForm;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteEntity mapToEntity(ClienteForm clienteForm){

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteForm.getNome());
        clienteEntity.setSobrenome(clienteForm.getSobrenome());
        clienteEntity.setCpf(clienteForm.getCpf());
        clienteEntity.setDataNascimento(clienteForm.getDataNascimento());

        return clienteEntity;
    }


    public static ClienteDto mapToDto(ClienteEntity clienteEntity){

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(clienteEntity.getId());
        clienteDto.setNome(clienteDto.getNome());
        clienteDto.setSobrenome(clienteDto.getSobrenome());
        clienteDto.setCpf(clienteDto.getCpf());
        clienteDto.setDataNascimento(clienteDto.getDataNascimento());
        clienteDto.setDataCadastro(clienteEntity.getDataCadastro());

        return clienteDto;
    }

    public static List<ClienteDto> mapToListDto(List<ClienteEntity> clientes){
        return clientes.stream().map(ClienteMapper::mapToDto).collect(Collectors.toList());
    }

}
