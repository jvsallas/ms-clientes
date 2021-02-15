package br.com.mercadosallas.clientes.mapper;

import br.com.mercadosallas.clientes.dto.AtualizacaoClienteForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.model.ClienteEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ClienteEntity mapToEntity(ClienteForm clienteForm){

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteForm.getNome());
        clienteEntity.setSobrenome(clienteForm.getSobrenome());
        clienteEntity.setCpf(clienteForm.getCpf());
        clienteEntity.setEmail(clienteForm.getEmail());
        clienteEntity.setDataNascimento(formatarData(clienteForm.getDataNascimento()));

        return clienteEntity;
    }

    private static LocalDate formatarData(String dataString) {
        return LocalDate.parse(dataString, formatter);
    }

    private static String formatarData(LocalDate data) {
        return data.format(formatter);
    }


    public static ClienteDto mapToDto(ClienteEntity clienteEntity){

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(clienteEntity.getId());
        clienteDto.setNome(clienteEntity.getNome());
        clienteDto.setSobrenome(clienteEntity.getSobrenome());
        clienteDto.setCpf(clienteEntity.getCpf());
        clienteDto.setEmail(clienteEntity.getEmail());
        clienteDto.setDataNascimento(formatarData(clienteEntity.getDataNascimento()));
        clienteDto.setDataCadastro(formatarData(clienteEntity.getDataCadastro()));

        return clienteDto;
    }

    public static ClienteEntity mapFromAtualizacao(AtualizacaoClienteForm formAtualizado, ClienteEntity clienteEntity){
        clienteEntity.setNome(formAtualizado.getNome());
        clienteEntity.setSobrenome(formAtualizado.getSobrenome());
        clienteEntity.setEmail(formAtualizado.getEmail());
        clienteEntity.setDataNascimento(formatarData(formAtualizado.getDataNascimento()));
        return clienteEntity;
    }

    public static List<ClienteDto> mapToListDto(List<ClienteEntity> clientes){
        return clientes.stream().map(ClienteMapper::mapToDto).collect(Collectors.toList());
    }

}
