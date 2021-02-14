package br.com.mercadosallas.clientes.mapper;

import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.dto.ClienteForm;

public class ClienteMapper {

    public static ClienteEntity mapToEntity(ClienteForm clienteForm){

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteForm.getNome());
        clienteEntity.setSobrenome(clienteForm.getSobrenome());
        clienteEntity.setCpf(clienteForm.getCpf());
        clienteEntity.setDataNascimento(clienteForm.getDataNascimento());

        return clienteEntity;
    }

}
