package br.com.mercadosallas.clientes.mapper;

import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.utils.DataUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteEntity mapToEntity(ClienteForm clienteForm){

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteForm.getNome());
        clienteEntity.setSobrenome(clienteForm.getSobrenome());
        clienteEntity.setCpf(clienteForm.getCpf());
        clienteEntity.setEmail(clienteForm.getEmail());
        clienteEntity.setDataNascimento(DataUtils.formatar(clienteForm.getDataNascimento()));
        clienteEntity.setTelefones(TelefoneMapper.mapToListEntity(clienteForm.getTelefones()));

        return clienteEntity;
    }


    public static ClienteDto mapToDto(ClienteEntity clienteEntity){

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(clienteEntity.getId());
        clienteDto.setNome(clienteEntity.getNome());
        clienteDto.setSobrenome(clienteEntity.getSobrenome());
        clienteDto.setCpf(clienteEntity.getCpf());
        clienteDto.setEmail(clienteEntity.getEmail());
        clienteDto.setDataNascimento(DataUtils.formatar(clienteEntity.getDataNascimento()));
        clienteDto.setDataCadastro(DataUtils.formatar(clienteEntity.getDataCadastro()));
        clienteDto.setTelefones(TelefoneMapper.mapToListDto(clienteEntity.getTelefones()));

        return clienteDto;
    }

    public static List<ClienteDto> mapToListDto(List<ClienteEntity> clientes){
        return clientes.stream().map(ClienteMapper::mapToDto).collect(Collectors.toList());
    }

}
