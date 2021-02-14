package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.model.ClienteRequestDto;
import br.com.mercadosallas.clientes.model.ClienteResponseDto;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDto adicionarCliente(ClienteRequestDto clienteRequestDto) {

        ClienteEntity clienteEntity = ClienteMapper.mapToEntity(clienteRequestDto);
        ClienteEntity save = clienteRepository.save(clienteEntity);
        return ClienteMapper.mapToResponse(save);
    }

}
