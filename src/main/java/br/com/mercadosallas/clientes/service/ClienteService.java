package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.model.ClienteRequestDto;
import br.com.mercadosallas.clientes.model.ClienteResponseDto;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDto adicionarCliente(ClienteRequestDto clienteRequestDto) {

        ClienteEntity entity = clienteRepository.save(ClienteMapper.mapToEntity(clienteRequestDto));

        return new ClienteResponseDto(entity);
    }

    public List<ClienteResponseDto> listarClientes() {

        List<ClienteEntity> clientes = clienteRepository.findAll();

        return ClienteResponseDto.converter(clientes);

    }

}
